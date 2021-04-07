package frc.team1983.commands;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;

public class SetTranslation extends InstantCommand
{
	public SetTranslation(Drivebase drivebase, Translation2d translation)
	{
		super(() -> drivebase.setTranslation(translation));
	}

	public SetTranslation(Translation2d translation)
	{
		this(Robot.getInstance().getDrivebase(), translation);
	}
}
