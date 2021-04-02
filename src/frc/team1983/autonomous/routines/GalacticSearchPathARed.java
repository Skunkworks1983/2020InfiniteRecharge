package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.GalacticSearchPathARedStartToFinish;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class GalacticSearchPathARed extends SequentialCommandGroup
{
	public GalacticSearchPathARed()
	{
		addCommands(
			new SetPose(Constants.Pose.GALACTIC_SEARCH_PATH_A_RED_START),
			new GalacticSearchPathARedStartToFinish()
		);
	}
}
