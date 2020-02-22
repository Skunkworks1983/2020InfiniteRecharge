package frc.team1983.commands;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;

public class SetPose extends InstantCommand
{
	public SetPose(Drivebase drivebase, Pose2d pose)
	{
		super(() -> drivebase.setPose(pose));
	}

	public SetPose(Pose2d pose)
	{
		this(Robot.getInstance().getDrivebase(), pose);
	}
}
