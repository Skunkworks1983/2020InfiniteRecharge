package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.SlalomStartToFinish;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class Slalom extends SequentialCommandGroup
{
	public Slalom()
	{
		addCommands(
			new SetPose(Constants.Pose.SLALOM_START_ZONE),
			new SlalomStartToFinish()
		);
	}
}
