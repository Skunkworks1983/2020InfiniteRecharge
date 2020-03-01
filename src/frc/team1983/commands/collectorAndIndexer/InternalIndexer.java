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

    public InternalIndexer(Indexer i, ControlMode cm, double iiv)
    {
        indexer = i;
        controlMode = cm;
        internalValue = iiv;
    }

    public InternalIndexer(ControlMode controlMode, double internalIndexerValue)
    {
        this(Robot.getInstance().getIndexer(), controlMode, internalIndexerValue);
    }


    @Override
    public void initialize()
    {
        indexer.setVoltageRamp(indexer.voltageRamp);
    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {
       indexer.setInternal(Indexer.internalForward);
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
