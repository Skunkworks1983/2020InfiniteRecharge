package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.OI;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;

public class RunTankDrive extends CommandBase
{
    private Drivebase drivebase;
    private OI oi;

    public RunTankDrive(Drivebase drivebase, OI oi)
    {
        this.drivebase = drivebase;
        this.oi = oi;
    }

    public RunTankDrive()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getOi());
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        double left = oi.getLeft();
        double right = oi.getRight();
        System.out.println(left + ", " + right);
        drivebase.set(left, right);
    }

    @Override
    public void end(boolean interrupted)
    {
        drivebase.set(0.0, 0.0);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
