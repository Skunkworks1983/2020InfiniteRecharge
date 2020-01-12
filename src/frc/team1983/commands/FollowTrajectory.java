package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;

import java.util.Arrays;

public class FollowTrajectory extends RamseteCommand
{
	public static final double MAX_VELOCITY = 2.0; // f/s TODO: calculate
	public static final double MAX_ACCELERATION = 2.0; // f/s^2 TODO: calculate
	public static final double B = 2.0, ZETA = 0.7;

	public FollowTrajectory(Drivebase drivebase, Pose2d... waypoints)
	{
		super(
			TrajectoryGenerator.generateTrajectory(
				Arrays.asList(waypoints),
				new TrajectoryConfig(
					Units.feetToMeters(MAX_VELOCITY),
					Units.feetToMeters(MAX_ACCELERATION)
				)
			),
			drivebase::getPose,
			new RamseteController(B, ZETA),
			drivebase.getFeedforward(),
			drivebase.getKinematics(),
			drivebase::getSpeeds,
			drivebase.getLeftPIDController(),
			drivebase.getRightPIDController(),
			drivebase::setVolts,
			drivebase
		);
	}

	public FollowTrajectory(Pose2d... waypoints)
	{
		this(Robot.getInstance().getDrivebase(), waypoints);
	}
}
