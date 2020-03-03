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
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {

       // isShooting = oi.isShooting();

        boolean isShooting = oi.getButton(OI.Joysticks.OPERATOR, 1).get();

        indexer.setCollectorTransfer(Indexer.motorsForward);

        //if shooting
        if (isShooting)
        {
            indexer.setShooterTransfer(0.75);
            indexer.setInternal(0.65);
        }
        else if (indexer.SHOOTER_TRANSFER_HAS_BALL.get()) //if we aren't shooting and sensor is triggered
        {
            System.out.println("shooter has ball");
           indexer.setShooterTransfer(Indexer.motorsOff);
           if (indexer.INTERNAL_INDEXER_HAS_BALL.get())
           {
               indexer.setInternal(Indexer.motorsOff);
           }
           else
           {
               indexer.setInternal(0.6);
           }
        }
        else
        {
            System.out.println("running transfer");
            indexer.setShooterTransfer(0.65);
            indexer.setInternal(0.6);
        }
        collector.setCollectorMotor(0.5);
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
        collector.setCollectorMotor(0);
    }
}
