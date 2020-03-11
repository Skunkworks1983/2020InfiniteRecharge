package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfPortToShootInFrontOfPort;
import frc.team1983.autonomous.paths.InFrontOfPortToTrenchBall3;
import frc.team1983.autonomous.paths.ShootInFrontOfPortToInFrontOfPort;
import frc.team1983.autonomous.paths.TrenchBall3ToShootInFrontOfPort;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.commands.collectorAndIndexer.IndexerStartupAuto;
import frc.team1983.commands.collectorAndIndexer.LoadIndexerAuto;
import frc.team1983.commands.collectorAndIndexer.ManualIndexer;
import frc.team1983.commands.collectorAndIndexer.SetCollectorPosition;
import frc.team1983.commands.shooter.SetArticulationPosition;
import frc.team1983.commands.shooter.SetShooter;
import frc.team1983.constants.Constants;
import frc.team1983.subsystems.Shooter;

public class InFrontOfPortToShootInFrontOfPortToTrenchToShootInFrontOfPort extends SequentialCommandGroup
{
	public InFrontOfPortToShootInFrontOfPortToTrenchToShootInFrontOfPort()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_PORT),
			new SetCollectorPosition(true),
			new ParallelRaceGroup(
				new SequentialCommandGroup(
					new InFrontOfPortToShootInFrontOfPort(),
					new TargetAlignment(180).withTimeout(2.0),
					new IndexerStartupAuto(true).withTimeout(2.0)
				),
				new SetArticulationPosition(Shooter.IN_FRONT_OF_PORT_CLOSE_PILLAR),
				new SetShooter(0.9, 0.9)
			),
			new ShootInFrontOfPortToInFrontOfPort(),
			new ParallelRaceGroup(
				new InFrontOfPortToTrenchBall3(),
				new LoadIndexerAuto(false)
			),
			new ParallelRaceGroup(
				new SequentialCommandGroup(
					 new TrenchBall3ToShootInFrontOfPort(),
					 new TargetAlignment(180).withTimeout(2.0),
					 new IndexerStartupAuto(true).withTimeout(5.0)
				),
				new SetArticulationPosition(Shooter.IN_FRONT_OF_PORT_CLOSE_PILLAR),
				new ManualIndexer(-0.15,-0.15,-0.15).withTimeout(0.1).andThen(
					new SetShooter(0.9, 0.9)
				)
			)
		);
	}
}
