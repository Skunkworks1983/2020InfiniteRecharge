package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Indexer;

public class UnloadIndexer extends CommandBase
{
    private Indexer indexer;

    public UnloadIndexer(Indexer indexer)
    {
        this.indexer = indexer;
    }

    public UnloadIndexer()
    {
        this(Robot.getInstance().getIndexer());
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        indexer.setShooterTransfer(Indexer.motorsReversed);
        indexer.setInternal(Indexer.internalReversed);
        indexer.setCollectorTransfer(Indexer.motorsReversed);
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
