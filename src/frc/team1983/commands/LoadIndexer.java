package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class LoadIndexer extends CommandBase
{
    //command moves balls from indexer to shooter
    //if shoot is false, balls are held once proximity sensor is triggered
    //if shoot is true, balls are passed through to the shooter

    private Indexer indexer;
    private OI oi;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexer(Indexer i, OI anOI, boolean shoot)
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

        if (!isShooting && indexer.indexerHasBall.get()) //if we aren't shooting and sensor is triggered
        {
            indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsOff);
        }
        else //we are shooting and motors all are turned on
        {
            indexer.shooterTransfer.set(ControlMode.Throttle, Indexer.motorsForward);
        }

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
