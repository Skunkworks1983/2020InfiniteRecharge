package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class ManualIndexer extends CommandBase
{
    private Indexer indexer;
    private OI oi;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public ManualIndexer(Indexer i, OI anOI, boolean shoot)
    {
        indexer = i;
        oi = anOI;

        isShooting = shoot;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {
        indexer.collectorTransfer.set(ControlMode.Throttle, Indexer.motorsForward);

        indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsForward);

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
