package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

//this class is to be used in auto
//in order for it to actually end, it must be passed a timeout in the auto command
public class LoadIndexerAuto extends CommandBase
{
    private Indexer indexer;
    private double indexerValue;
    private ControlMode controlMode;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexerAuto(Indexer i, double indexerPercentThrottle, boolean isShooting)
    {
        indexer = i;
        indexerValue = indexerPercentThrottle;
        this.isShooting = isShooting;
    }

    public LoadIndexerAuto(double indexerPercentThrottle, boolean isShooting)
    {
        this(Robot.getInstance().getIndexer(), indexerPercentThrottle, isShooting);
    }


    @Override
    public void initialize()
    {

    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {

        indexer.setCollectorTransfer(Indexer.motorsForward);
        indexer.setInternal(Indexer.internalForward);

        if (!isShooting && indexer.indexerHasBall.get()) //if we aren't shooting and sensor is triggered
        {
            indexer.setShooterTransfer(Indexer.motorsOff);
        }
        else //we are shooting and motors all are turned on
        {
            indexer.setShooterTransfer(Indexer.motorsForward);
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
        indexer.setShooterTransfer(Indexer.motorsOff);
        indexer.setInternal(Indexer.motorsOff);
        indexer.setCollectorTransfer(Indexer.motorsOff);
    }
}
