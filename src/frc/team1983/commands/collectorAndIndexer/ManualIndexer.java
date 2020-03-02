package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

import javax.naming.ldap.Control;

public class ManualIndexer extends CommandBase
{
    private Indexer indexer;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public ManualIndexer(Indexer indexer)
    {
        this.indexer = indexer;

    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        indexer.setCollectorTransfer(Indexer.motorsForward);
        indexer.setShooterTransfer(Indexer.motorsForward);
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
        indexer.setShooterTransfer(Indexer.motorsOff);
        indexer.setInternal(Indexer.motorsOff);
        indexer.setCollectorTransfer(Indexer.motorsOff);
    }
}
