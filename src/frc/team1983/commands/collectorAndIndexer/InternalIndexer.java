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
    private ControlMode controlMode;
    private double internalValue;
    private OI oi;
    private double delayTime;
    private double startTime;

    public InternalIndexer(Indexer i, ControlMode cm, double iiv, double dt)
    {
        indexer = i;
        controlMode = cm;
        internalValue = iiv;
        delayTime = dt;
    }

    public InternalIndexer(ControlMode controlMode, double indexerValue, double delayTime)
    {
        this(Robot.getInstance().getIndexer(), controlMode, indexerValue, delayTime);
    }


    @Override
    public void initialize()
    {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {
        //indexer.internal.set(ControlMode.Throttle, Indexer.internalForward);

        indexer.internal.set(controlMode, internalValue);

    }

    @Override
    public boolean isFinished()
    {
        if (delayTime == 0)
        {
            return false;
        }

        if (Timer.getFPGATimestamp() - startTime >= delayTime)
        {
            return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        indexer.internal.set(ControlMode.Throttle, Indexer.motorsOff);
    }
}
