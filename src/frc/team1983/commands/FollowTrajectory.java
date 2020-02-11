package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
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
	public static final double MAX_VELOCITY = 8.0; // f/s TODO: calculate
	public static final double MAX_ACCELERATION = 8.0; // f/s^2 TODO: calculate
	public static final double B = 2.0, ZETA = 0.7;

	private Drivebase drivebase;
	private TrajectoryConfig trajectoryConfig;
	private RamseteController ramseteController;
	private RamseteCommand ramseteCommand;

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

		ramseteCommand = new RamseteCommand(
			TrajectoryGenerator.generateTrajectory(
				Arrays.asList(waypoints),
				trajectoryConfig
			),
			drivebase::getPose,
			ramseteController,
			drivebase.getFeedforward(),
			drivebase.getKinematics(),
			drivebase::getSpeeds,
			drivebase.getLeftPIDController(),
			drivebase.getRightPIDController(),
			drivebase::setVolts,
			drivebase
		);
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
	}

	@Override
	public void execute()
	{
		ramseteCommand.execute();
	}

	@Override
	public void end(boolean interrupted)
	{
		ramseteCommand.end(interrupted);
		drivebase.set(ControlMode.Throttle, 0.0, 0.0);
	}

	@Override
	public boolean isFinished()
	{
		return ramseteCommand.isFinished();
	}
}