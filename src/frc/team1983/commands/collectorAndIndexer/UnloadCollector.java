package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;

public class UnloadCollector extends CommandBase
{
    private Collector collector;

    public UnloadCollector(Collector collector)
    {
        this.collector = collector;
    }

    public UnloadCollector()
    {
        this(Robot.getInstance().getCollector());
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        collector.setCollectorMotor(Collector.motorsReversed);
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
