package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class IndexerStartupAuto extends SequentialCommandGroup
{
    public IndexerStartupAuto(Indexer i, double internalPercentThrottle, double outsidePercentThrottle,
                              boolean isShooting, double delaySeconds)
    {
        addCommands(
                new InternalIndexer(i, ControlMode.Throttle, internalPercentThrottle).withTimeout(delaySeconds),
                new LoadIndexerAuto(i, outsidePercentThrottle, isShooting)
        );
    }
}
