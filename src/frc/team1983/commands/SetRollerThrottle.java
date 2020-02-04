package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;

public class SetRollerThrottle extends CommandBase
{
    private Collector collector;
    private double throttle;

    public SetRoller(Collector collector, double throttle)
    {
        this.collector = collector;
        this.throttle = throttle;
    }

    public SetCollectorRollerThrottle(double throttle)
    {
        this(Robot.getInstance().getCollector(), throttle);
    }

    @Override
    protected void initialize()
    {
        collector.setRollerThrottle(throttle);
    }

    @Override
    protected void end()
    {
        collector.setRollerThrottle(0);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
