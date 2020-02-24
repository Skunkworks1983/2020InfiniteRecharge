package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class UnloadIndexerAndCollector extends ParallelCommandGroup
{
    public UnloadIndexerAndCollector (Collector c, Indexer i, ControlMode cm, double cv, double iv)
    {
        addCommands
                (
                        new UnloadIndexer(cm, iv),

                        new UnloadCollector(cm, cv)
                );
    }
    
}
