package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class UnloadCollector extends CommandBase
{
    private Collector collector;
    private OI oi;

    public UnloadCollector(Collector c, OI anOI)
    {
        collector = c;
        oi = anOI;
    }

    @Override //when we have motors reversed, then they're always in initialize
    public void initialize()
    {
        collector.roller.set(ControlMode.Throttle, Collector.motorsReversed);
    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {

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
