package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;

public class SetCollectorPosition extends InstantCommand
{
    public SetCollectorPosition(Collector collector, boolean collectorDown)
    {
        super(() -> collector.setCollectorPosition(collectorDown), collector);
    }
    public SetCollectorPosition(boolean collectorDown)
    {
        this(Robot.getInstance().getCollector(), collectorDown);
    }
}
