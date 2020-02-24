package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;

public class CollectAndLoad extends ParallelCommandGroup
{


    public CollectAndLoad(Collector c, Indexer i, Double cv, Double iv)
    {
        addCommands(
                new SetRollerThrottle(c, cv),

                new LoadIndexer(iv)
        );
    }

}
