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

    public LoadIndexerAuto(Indexer i, boolean isShooting)
    {
        indexer = i;
        this.isShooting = isShooting;
    }

    public LoadIndexerAuto(boolean isShooting)
    {
        this(Robot.getInstance().getIndexer(), isShooting);
    }


    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {

        indexer.setCollectorTransfer(Indexer.motorsForward);

        //if shooting
        if (isShooting)
        {
            indexer.setShooterTransfer(0.75);
        }
        else if (indexer.SHOOTER_TRANSFER_HAS_BALL.get()) //if we aren't shooting and sensor is triggered
        {
            indexer.setShooterTransfer(Indexer.motorsOff);
        }
        else //if indexing and no ball detected
        {
            indexer.setShooterTransfer(0.5);
        }

        if(isShooting)
        {
            indexer.setInternal(0.65);
        }
        else if(indexer.INTERNAL_INDEXER_HAS_BALL.get())
        {
            indexer.setInternal(Indexer.motorsOff);
        }
        else
        {
            indexer.setInternal(0.4);
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
