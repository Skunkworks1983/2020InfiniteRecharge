package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class ManualIndexer extends CommandBase
{
    private Indexer indexer;
    private double collectorTransferSpeed;
    private double internalIndexerSpeed;
    private double shooterTransferSpeed;

    public ManualIndexer(Indexer indexer, double collectorTransferSpeed, double internalIndexerSpeed, double shooterTransferSpeed)
    {
        this.indexer = indexer;
        this.collectorTransferSpeed = collectorTransferSpeed;
        this.internalIndexerSpeed = internalIndexerSpeed;
        this.shooterTransferSpeed = shooterTransferSpeed;
    }

    public ManualIndexer(double collectorTransferSpeed, double internalIndexerSpeed, double shooterTransferSpeed)
    {
        this(Robot.getInstance().getIndexer(), collectorTransferSpeed, internalIndexerSpeed, shooterTransferSpeed);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        indexer.setCollectorTransfer(collectorTransferSpeed);
        indexer.setInternal(internalIndexerSpeed);
        indexer.setShooterTransfer(shooterTransferSpeed);
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
