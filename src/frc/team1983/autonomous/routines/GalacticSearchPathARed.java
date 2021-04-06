package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.GalacticSearchPathARedA6ToFinish;
import frc.team1983.autonomous.paths.GalacticSearchPathARedStartToA6;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.collectorAndIndexer.LoadIndexerAuto;
import frc.team1983.commands.collectorAndIndexer.ToggleCollectorPosition;
import frc.team1983.constants.Constants;

public class GalacticSearchPathARed extends SequentialCommandGroup
{
	public GalacticSearchPathARed()
	{
		addCommands(
			new SetPose(Constants.Pose.GALACTIC_SEARCH_PATH_A_RED_START),
			new ToggleCollectorPosition(),
			new ParallelCommandGroup(
				new LoadIndexerAuto(false),
				new SequentialCommandGroup(
					new GalacticSearchPathARedStartToA6().withTimeout(2.0),
					new GalacticSearchPathARedA6ToFinish()
				)
			)
		);
	}
}
