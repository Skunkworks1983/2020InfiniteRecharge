package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class LoadIndexerTele extends CommandBase
{
    //command moves balls from indexer to shooter
    //if shoot is false, balls are held once proximity sensor is triggered
    //if shoot is true, balls are passed through to the shooter

    private Indexer indexer;
    private Collector collector;
    private OI oi;
    private ControlMode controlMode;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexerTele(Indexer i, Collector collector, OI anOI)
    {
        indexer = i;
        this.collector = collector;
        oi = anOI;
    }

    public LoadIndexerTele()
    {
        this(Robot.getInstance().getIndexer(), Robot.getInstance().getCollector(), Robot.getInstance().getOI());
    }


    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {

       // isShooting = oi.isShooting();

        boolean isShooting = oi.getButton(OI.Joysticks.OPERATOR, 1).get();



        //if shooting
        if (isShooting)
        {
            indexer.setShooterTransfer(0.75);
            indexer.setInternal(0.5);
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
        if(oi.getButton(OI.Joysticks.OPERATOR, 1).get())
        {
            indexer.setShooterTransfer(0.75);
            indexer.setInternal(0.65);
        }
        else
        {
            indexer.setShooterTransfer(Indexer.motorsOff);
            indexer.setInternal(Indexer.motorsOff);
            indexer.setCollectorTransfer(Indexer.motorsOff);
            collector.setCollectorMotor(0);
        }
    }
}
