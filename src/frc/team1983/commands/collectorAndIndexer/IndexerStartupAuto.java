package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class IndexerStartupAuto extends ParallelCommandGroup
{
    public IndexerStartupAuto(boolean isShooting)
    {
        addCommands(
//                new InternalIndexer(i, internalPercentThrottle).withTimeout(delaySeconds),
            new LoadIndexerAuto(Robot.getInstance().getIndexer(), Robot.getInstance().getCollector(), isShooting),
            new SequentialCommandGroup(
                new WaitCommand(0.75),
                //Collector transfer needs to be negative here because it goes in reverse here
                new CollectorTransfer(0.4)
            ),
            new SequentialCommandGroup(
                new WaitCommand(1),
                new SetRollerThrottle(Robot.getInstance().getCollector(), 0.3)
            )
        );
    }
}
