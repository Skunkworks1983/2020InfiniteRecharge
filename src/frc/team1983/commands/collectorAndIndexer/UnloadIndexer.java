package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

import javax.naming.ldap.Control;

public class UnloadIndexer extends CommandBase
{
    private Indexer indexer;
    private ControlMode controlMode;
    private double indexerValue;
    private double internalIndexerValue;
    private OI oi;

    public UnloadIndexer(Indexer i, ControlMode cm, double iv, double iiv)
    {
        indexer = i;
        controlMode = cm;
        indexerValue = iv;
        internalIndexerValue = iiv;
    }

    public UnloadIndexer(ControlMode controlMode, double indexerValue, double internalIndexerValue)
    {
        this(Robot.getInstance().getIndexer(), controlMode, indexerValue, internalIndexerValue);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {
        indexer.collectorTransfer.set(controlMode, indexerValue); //TODO: revert to original once tuned
        indexer.shooterTransfer.set(controlMode, indexerValue); //TODO: revert to original once tuned
        indexer.internal.set(controlMode, internalIndexerValue);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        indexer.collectorTransfer.set(ControlMode.Throttle, Indexer.motorsOff);
        indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsOff);
        indexer.internal.set(ControlMode.Throttle, 0.0);
    }
}
