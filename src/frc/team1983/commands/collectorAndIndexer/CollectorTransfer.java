package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Indexer;

public class CollectorTransfer extends CommandBase
{
    private Indexer indexer;
    private double throttle;

    public CollectorTransfer(Indexer indexer, double throttle)
    {
        this.indexer = indexer;
        this.throttle = throttle;

    }

    public CollectorTransfer(double collectorTransferSpeed)
    {
        this(Robot.getInstance().getIndexer(), collectorTransferSpeed);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        indexer.setCollectorTransfer(throttle);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        indexer.setCollectorTransfer(Indexer.motorsOff);
    }
}
