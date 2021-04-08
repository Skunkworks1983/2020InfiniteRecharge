package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.GalacticSearchPathBRedStartToFinish;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.collectorAndIndexer.LoadIndexerAuto;
import frc.team1983.commands.collectorAndIndexer.ToggleCollectorPosition;
import frc.team1983.constants.Constants;

public class GalacticSearchPathBRed extends SequentialCommandGroup
{
	public GalacticSearchPathBRed()
	{
		addCommands(
			new SetPose(Constants.Pose.GALACTIC_SEARCH_PATH_B_RED_START),
			new ToggleCollectorPosition(),
			new ParallelCommandGroup(
				new LoadIndexerAuto(false),
				new GalacticSearchPathBRedStartToFinish()
			)
		);
	}
}