package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;

public class ToggleCollectorPosition extends InstantCommand
{
    public ToggleCollectorPosition(Collector collector)
    {
        super(() -> collector.setCollectorPosition(!collector.isCollectorDown()), collector);
    }
    public ToggleCollectorPosition()
    {
        this(Robot.getInstance().getCollector());
    }
}
