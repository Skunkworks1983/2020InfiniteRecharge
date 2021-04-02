package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.GalacticSearchPathBBlueStartToFinish;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class GalacticSearchPathBBlue extends SequentialCommandGroup
{
	public GalacticSearchPathBBlue()
	{
		addCommands(
			new SetPose(Constants.Pose.GALACTIC_SEARCH_PATH_B_BLUE_START),
			new GalacticSearchPathBBlueStartToFinish()
		);
	}
}
