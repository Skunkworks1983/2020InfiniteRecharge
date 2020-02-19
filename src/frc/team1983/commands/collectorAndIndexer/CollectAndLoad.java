package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;

public class CollectAndLoad extends ParallelCommandGroup
{

    public CollectAndLoad(Collector c, Indexer i, OI anOI, Boolean shoot)
    {
        addCommands(
                new SetRollerThrottle(c, Collector.motorsForward),

                new LoadIndexer(i, anOI, shoot)
        );
    }

}
