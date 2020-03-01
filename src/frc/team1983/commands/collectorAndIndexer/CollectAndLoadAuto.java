package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;

public class CollectAndLoadAuto extends ParallelCommandGroup
{
    public CollectAndLoadAuto(Collector c, Indexer i, double collectorPercentThrottle,
                              double internalPercentThrottle, double externalPercentThrottle, boolean isShooting,
                              double delaySeconds)
    {
        addCommands(
                new SetRollerThrottle(c, collectorPercentThrottle),

                new IndexerStartupAuto(i, internalPercentThrottle, externalPercentThrottle, isShooting, delaySeconds)
        );
    }
}
