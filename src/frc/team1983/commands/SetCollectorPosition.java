package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;

public class SetCollectorPosition
{
    public class SetCollector extends InstantCommand
    {
        public SetCollector(Collector collector, Collector.position p)
        {
            collector.setCollectorPosition(p);
        }
    }
}
