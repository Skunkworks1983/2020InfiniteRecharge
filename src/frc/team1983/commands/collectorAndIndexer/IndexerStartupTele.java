package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class IndexerStartupTele extends SequentialCommandGroup
{
    public IndexerStartupTele(Indexer i, OI anOI, double internalPercentThrottle, double outsidePercentThrottle,
                              double delaySeconds)
    {
        addCommands(
                new InternalIndexer(i, ControlMode.Throttle, internalPercentThrottle).withTimeout(delaySeconds),
                new LoadIndexerTele(i, anOI, outsidePercentThrottle)
        );
    }
}
