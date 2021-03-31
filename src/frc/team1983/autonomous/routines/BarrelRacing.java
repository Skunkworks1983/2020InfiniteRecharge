package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.BarrelRacingPath;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class BarrelRacing extends SequentialCommandGroup
{
	public BarrelRacing()
	{
		addCommands(
			new SetPose(Constants.Pose.BARREL_RACING_START_ZONE),
			new BarrelRacingPath()
		);
	}
}
