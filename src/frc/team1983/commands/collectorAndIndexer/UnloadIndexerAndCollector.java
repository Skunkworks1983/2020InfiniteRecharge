package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class UnloadIndexerAndCollector extends ParallelCommandGroup
{
    public UnloadIndexerAndCollector ()
    {
        addCommands
                (
                        new UnloadIndexer(),

                        new UnloadCollector()
                );
    }
    
}
