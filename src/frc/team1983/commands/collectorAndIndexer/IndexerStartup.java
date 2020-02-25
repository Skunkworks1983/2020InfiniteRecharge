package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class IndexerStartup extends SequentialCommandGroup
{
    public IndexerStartup(Indexer i, OI anOI, double internalPercentThrottle, double outsidePercentThrottle,
                          double delaySeconds)
    {
        addCommands(new InternalIndexer(i, ControlMode.Throttle, internalPercentThrottle, delaySeconds));

        andThen(new LoadIndexer(i, anOI, outsidePercentThrottle));
    }
}
