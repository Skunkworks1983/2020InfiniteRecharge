package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.GalacticSearchPathABlueStartToFinish;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class GalacticSearchPathABlue extends SequentialCommandGroup
{
	public GalacticSearchPathABlue()
	{
		addCommands(
			new SetPose(Constants.Pose.GALACTIC_SEARCH_PATH_A_BLUE_START),
			new GalacticSearchPathABlueStartToFinish()
		);
	}
}
