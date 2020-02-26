package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;

public class RunGyroDrive extends CommandBase
{
    public static final double JOYSTICK_DEADZONE = 0.05;

    private Drivebase drivebase;
    private OI oi;

    private double targetHeading = 0;

    public RunGyroDrive(Drivebase drivebase, OI oi)
    {
        this.drivebase = drivebase;
        this.oi = oi;

        addRequirements(drivebase);
    }

    public RunGyroDrive()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getOI());
    }

    @Override
    public void initialize()
    {
        targetHeading = drivebase.getHeading().getDegrees();
    }

    @Override
    public void execute()
    {
        boolean slow = !oi.getButton(OI.Joysticks.LEFT, 1).get();

        double currentHeading = drivebase.getHeading().getDegrees();
        double turnThrottle = 0.02 * (currentHeading - targetHeading);
        double driveThrottle = oi.getRightY() * (slow ? 0.65 : 1);

        if(Math.abs(oi.getLeftX()) > JOYSTICK_DEADZONE)
        {
            turnThrottle = oi.getLeftX() * (slow ? 0.2 : 0.4);
            targetHeading = drivebase.getHeading().getDegrees();
        }

        drivebase.setLeft(ControlMode.Throttle, driveThrottle + turnThrottle);
        drivebase.setRight(ControlMode.Throttle,driveThrottle - turnThrottle);
    }
}
