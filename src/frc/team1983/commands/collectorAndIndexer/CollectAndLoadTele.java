package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;

public class CollectAndLoadTele extends ParallelCommandGroup
{

    public CollectAndLoadTele(Collector c, Indexer i, OI anOI, double collectorPercentThrottle,
                              double internalPercentThrottle, double delaySeconds)
    {
        addCommands(
                new SetRollerThrottle(c, collectorPercentThrottle),

                new IndexerStartupTele(i, anOI, internalPercentThrottle, delaySeconds)
        );
    }

}
