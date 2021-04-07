package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team1983.Robot;

public class IndexerStartupTele extends ParallelCommandGroup
{
    public IndexerStartupTele()
    {
        addCommands(
            new LoadIndexerTele(Robot.getInstance().getIndexer(), Robot.getInstance().getCollector(), Robot.getInstance().getOI()),
            new SequentialCommandGroup(
                new WaitCommand(0.5 - 0.35),
                new InternalIndexer(0.5)
            ),
            new SequentialCommandGroup(
                new WaitCommand(0.75),
                new CollectorTransfer(0.4 + 0.1)
            ),
            new SequentialCommandGroup(
                new WaitCommand(1.15),
                new SetRollerThrottle(Robot.getInstance().getCollector(), 0.3)
            )
        );
    }
}
