package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;

public class SetRollerThrottle extends CommandBase
{
    private Collector collector;
    private double throttle;

    public SetRollerThrottle(Collector c, double t)
    {
        collector = c;
        throttle = t;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {
       // collector.setRollerThrottle(throttle);
        collector.roller.set(ControlMode.Throttle, Collector.motorsForward);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        collector.roller.set(ControlMode.Throttle, Collector.motorsOff);
    }

}
