package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

import javax.naming.ldap.Control;

public class UnloadIndexer extends CommandBase
{
    private Indexer indexer;
    private OI oi;


    public UnloadIndexer(Indexer i, OI anOI)
    {
        indexer = i;
        oi = anOI;
    }

    @Override
    public void initialize()
    {
        indexer.collectorTransfer.set(ControlMode.Throttle, Indexer.motorsReversed);
        indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsReversed);
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
        indexer.collectorTransfer.set(ControlMode.Throttle, Indexer.motorsOff);

        indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsOff);
    }
}
