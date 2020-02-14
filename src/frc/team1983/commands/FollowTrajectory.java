package frc.team1983.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;

import java.util.Arrays;

public class FollowTrajectory extends CommandBase
{
	public static final double MAX_VELOCITY = 5.0; // f/s TODO: calculate
	public static final double MAX_ACCELERATION = 4.0; // f/s^2 TODO: calculate
	public static final double B = 2.0, ZETA = 0.7;

	private final Timer timer = new Timer();
	private double prevTime;

	private Drivebase drivebase;
	private TrajectoryConfig trajectoryConfig;
	private Trajectory trajectory;
	private RamseteController ramseteController;
	private RamseteCommand ramseteCommand;
	private Pose2d lastPose;
	private DifferentialDriveWheelSpeeds prevSpeeds;

	public FollowTrajectory(Drivebase drivebase, boolean reversed, Pose2d... waypoints)
	{
		this.drivebase = drivebase;

		ramseteController = new RamseteController(B, ZETA);

		trajectoryConfig = new TrajectoryConfig(
			Units.feetToMeters(MAX_VELOCITY),
			Units.feetToMeters(MAX_ACCELERATION)
		);
		trajectoryConfig.setKinematics(drivebase.getKinematics());
		trajectoryConfig.setReversed(reversed);

		trajectory = TrajectoryGenerator.generateTrajectory(
			Arrays.asList(waypoints),
			trajectoryConfig
		);

		ramseteCommand = new RamseteCommand(
			TrajectoryGenerator.generateTrajectory(
				Arrays.asList(waypoints),
				trajectoryConfig
			),
			drivebase::getPose,
			ramseteController,
			drivebase.getKinematics(),
			(leftSpeedSetpoint, rightSpeedSetpoint) -> {
				double curTime = timer.get();
				double dt = curTime - prevTime;

				SmartDashboard.putNumber("Left Speed Setpoint", leftSpeedSetpoint);
				SmartDashboard.putNumber("Right Speed Setpoint", rightSpeedSetpoint);
				System.out.printf("Left Speed Setpoint: %f, Right Speed Setpoint: %f, ", leftSpeedSetpoint, rightSpeedSetpoint);

				DifferentialDriveWheelSpeeds targetWheelSpeeds = drivebase.getKinematics().toWheelSpeeds(
					ramseteController.calculate(drivebase.getPose(), trajectory.sample(curTime)));

				double leftFeedforward =
					drivebase.getFeedforward().calculate(leftSpeedSetpoint,
						(leftSpeedSetpoint - prevSpeeds.leftMetersPerSecond) / dt);

				double rightFeedforward =
					drivebase.getFeedforward().calculate(rightSpeedSetpoint,
						(rightSpeedSetpoint - prevSpeeds.rightMetersPerSecond) / dt);

				double leftOutput = leftFeedforward
					+ drivebase.getLeftPIDController().calculate(drivebase.getSpeeds().leftMetersPerSecond,
					leftSpeedSetpoint);

				double rightOutput = rightFeedforward
					+ drivebase.getRightPIDController().calculate(drivebase.getSpeeds().rightMetersPerSecond,
					rightSpeedSetpoint);

				drivebase.setVolts(leftOutput, rightOutput);

				SmartDashboard.putNumber("Left Output", leftOutput);
				SmartDashboard.putNumber("Right Output", rightOutput);
				System.out.printf("Left Output: %f, Right Output: %f, ", leftOutput, rightOutput);

				prevTime = curTime;
				prevSpeeds = targetWheelSpeeds;
			},
			drivebase
		);

		this.lastPose = waypoints[waypoints.length - 1];

		addRequirements(drivebase);
	}

	public FollowTrajectory(boolean reversed, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), reversed, waypoints);
	}

	public FollowTrajectory(Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), false, waypoints);
	}

	@Override
	public void initialize()
	{
		ramseteCommand.initialize();
		Trajectory.State initialState = trajectory.sample(0);
		prevSpeeds = drivebase.getKinematics().toWheelSpeeds(
			new ChassisSpeeds(initialState.velocityMetersPerSecond,
				0,
				initialState.curvatureRadPerMeter
					* initialState.velocityMetersPerSecond));
		timer.reset();
		timer.start();
	}

	@Override
	public void execute()
	{
		ramseteCommand.execute();
		SmartDashboard.putNumber("Pose Error", drivebase.getPose().getTranslation().getDistance(lastPose.getTranslation()));
		System.out.printf("Pose Error: %f \n", drivebase.getPose().getTranslation().getDistance(lastPose.getTranslation()));
	}

	@Override
	public void end(boolean interrupted)
	{
		ramseteCommand.end(interrupted);
		timer.stop();
		drivebase.set(ControlMode.Throttle, 0.0, 0.0);
		System.out.println("END");
	}

	@Override
	public boolean isFinished()
	{
		return ramseteCommand.isFinished();
	}
}