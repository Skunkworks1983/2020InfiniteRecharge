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
	public static final double MAX_ACCELERATION = 14.0; // f/s^2 TODO: calculate
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

	public FollowTrajectory(Drivebase drivebase, boolean reversed, double velocity, double acceleration, Pose2d... waypoints)
	{
		this.drivebase = drivebase;

		ramseteController = new RamseteController(B, ZETA);

		trajectoryConfig = new TrajectoryConfig(
			Units.feetToMeters(velocity),
			Units.feetToMeters(acceleration)
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

				drivebase.getLeftPIDController().setSetpoint(ControlMode.Velocity, convertRamseteVelocityToDrivebaseVelocity(leftSpeedSetpoint), leftFeedforward);
				drivebase.getRightPIDController().setSetpoint(ControlMode.Velocity, convertRamseteVelocityToDrivebaseVelocity(rightSpeedSetpoint), rightFeedforward);

				SmartDashboard.putNumber("Left Meters Per Second", drivebase.getLeftMetersPerSecond());
				SmartDashboard.putNumber("Right Meters Per Second", drivebase.getRightMetersPerSecond());
				System.out.printf("Left Meters Per Second: %f, Right Meters Per Second: %f, ", drivebase.getLeftMetersPerSecond(), drivebase.getRightMetersPerSecond());

				SmartDashboard.putNumber("Left Speed Error", leftSpeedSetpoint - drivebase.getLeftMetersPerSecond());
				SmartDashboard.putNumber("Right Speed Error", rightSpeedSetpoint - drivebase.getRightMetersPerSecond());
				System.out.printf("Left Speed Error: %f, Right Speed Error: %f, ", leftSpeedSetpoint - drivebase.getLeftMetersPerSecond(), rightSpeedSetpoint - drivebase.getRightMetersPerSecond());

				prevTime = curTime;
				prevSpeeds = targetWheelSpeeds;
			},
			drivebase
		);

		this.lastPose = waypoints[waypoints.length - 1];

		addRequirements(drivebase);
	}

	public FollowTrajectory(boolean reversed, double velocity, double acceleration, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), reversed, velocity, acceleration, waypoints);
	}

	public FollowTrajectory(double velocity, double acceleration, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), false, velocity, acceleration, waypoints);
	}

	public FollowTrajectory(boolean reversed, double velocity, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), reversed, velocity, MAX_ACCELERATION, waypoints);
	}

	public FollowTrajectory(double velocity, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), false, velocity, MAX_ACCELERATION, waypoints);
	}

	public FollowTrajectory(boolean reversed, Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), reversed, MAX_VELOCITY, MAX_ACCELERATION, waypoints);
	}

	public FollowTrajectory(Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), false, MAX_VELOCITY, MAX_ACCELERATION, waypoints);
	}

	private double convertRamseteVelocityToDrivebaseVelocity(double ramseteVelocity)
	{
		return ramseteVelocity / Drivebase.METERS_PER_TICK * 60;
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
		SmartDashboard.putNumber("X Error", drivebase.getPose().getTranslation().getX() - lastPose.getTranslation().getX());
		SmartDashboard.putNumber("Y Error", drivebase.getPose().getTranslation().getY() - lastPose.getTranslation().getY());

		System.out.printf("Pose Error: %f, ", drivebase.getPose().getTranslation().getDistance(lastPose.getTranslation()));
		System.out.printf("X Error: %f, ", drivebase.getPose().getTranslation().getX() - lastPose.getTranslation().getX());
		System.out.printf("Y Error: %f \n", drivebase.getPose().getTranslation().getY() - lastPose.getTranslation().getY());

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