package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class InternalIndexer extends CommandBase
{
    private Indexer indexer;
    private double throttle;

    public InternalIndexer(Indexer i,  double throttle)
    {
        this.indexer = i;
        this.throttle = throttle;
    }

    public InternalIndexer( double throttle)
    {
        this(Robot.getInstance().getIndexer(), throttle);
    }

    @Override
    public void initialize()
    {
        indexer.setVoltageRamp(indexer.voltageRamp);
    }

    @Override
    public void execute()
    {
        System.out.println("InternalIndexer running");
       indexer.setInternal(throttle);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        indexer.setInternal(Indexer.motorsOff);
    }
}
