package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.services.OI;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;

public class RunTankDrive extends CommandBase
{
    private Drivebase drivebase;
    private OI oi;

    public RunTankDrive(Drivebase drivebase, OI oi)
    {
        this.drivebase = drivebase;
        this.oi = oi;

        addRequirements(drivebase);
    }

    public RunTankDrive()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getOI());
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        double left = oi.getLeftY();
        double right = oi.getRightY();
        System.out.println(left + ", " + right);
        drivebase.set(ControlMode.Throttle, left, right);
    }

    @Override
    public void end(boolean interrupted)
    {
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
