package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.subsystems.Collector;

public class SetRollerThrottle extends CommandBase
{
    private Collector collector;
    private double throttle;

    public SetRollerThrottle(Collector collector, double throttle)
    {
        this.collector = collector;
        this.throttle = throttle;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        collector.setCollectorMotor(throttle);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        collector.setCollectorMotor(Collector.motorsOff);
    }

}
