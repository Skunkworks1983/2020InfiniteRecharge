package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

import javax.naming.ldap.Control;

public class LoadIndexer extends CommandBase
{
    //command moves balls from indexer to shooter
    //if shoot is false, balls are held once proximity sensor is triggered
    //if shoot is true, balls are passed through to the shooter

    private Indexer indexer;
    private OI oi;
    private ControlMode controlMode;

    private boolean isShooting; //this takes into account whether or not we are shooting

    public LoadIndexer(Indexer i, OI anOI, double iv)
    {
        indexer = i;
        oi = anOI;
    }

    public LoadIndexer(double iv)
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

        boolean isShooting = oi.getButton(OI.Joysticks.PANEL, OI.SET_SHOOTER).get();

        indexer.collectorTransfer.set(ControlMode.Throttle, Indexer.motorsForward);
        indexer.internal.set(ControlMode.Throttle, Indexer.internalForward);

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
        indexer.internal.set(ControlMode.Throttle, Indexer.motorsOff);
    }
}
