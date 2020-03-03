package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class LoadIndexerTele extends CommandBase
{
    //command moves balls from indexer to shooter
    //if shoot is false, balls are held once proximity sensor is triggered
    //if shoot is true, balls are passed through to the shooter

    private Indexer indexer;
    private OI oi;
    private ControlMode controlMode;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexerTele(Indexer i, OI anOI, double iv)
    {
        indexer = i;
        oi = anOI;
    }

    public LoadIndexerTele(double iv)
    {
        this(Robot.getInstance().getIndexer(), Robot.getInstance().getOI(), iv);
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
        else if (indexer.INTERNAL_INDEXER_HAS_BALL.get())
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
