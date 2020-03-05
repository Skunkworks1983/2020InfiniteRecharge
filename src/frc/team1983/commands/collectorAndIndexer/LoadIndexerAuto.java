package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;

//this class is to be used in auto
//in order for it to actually end, it must be passed a timeout in the auto command
public class LoadIndexerAuto extends CommandBase
{
    private Indexer indexer;
    public Collector collector;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexerAuto(Indexer indexer, Collector collector, boolean isShooting)
    {
        this.indexer = indexer;
        this.collector = collector;
        this.isShooting = isShooting;
    }

    public LoadIndexerAuto(boolean isShooting)
    {
        this(Robot.getInstance().getIndexer(), Robot.getInstance().getCollector(), isShooting);
    }


    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        //if shooting
        if (isShooting)
        {
            indexer.setShooterTransfer(0.8);
        }
        else if (indexer.ShooterTransferHasBall.get()) //if we aren't shooting and sensor is triggered
        {
            indexer.setShooterTransfer(Indexer.motorsOff);
            if (indexer.InternalIndexerHasBall.get())
            {
                indexer.setInternal(Indexer.motorsOff);
                indexer.setCollectorTransfer(0.3);
                collector.setCollectorMotor(0.3);
            }
            else
            {
                indexer.setInternal(0.6);
                indexer.setCollectorTransfer(0.3);
                collector.setCollectorMotor(0.3);
            }
        }
        else
        {
            indexer.setShooterTransfer(0.5);
            indexer.setInternal(0.6);
            indexer.setCollectorTransfer(0.2);
            collector.setCollectorMotor(0.2);
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
        collector.setRollerThrottle(Collector.motorsOff);
    }
}
