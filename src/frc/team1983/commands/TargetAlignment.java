package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Limelight;
import frc.team1983.util.sensors.NavX;

public class TargetAlignment extends PIDCommand
{
    private static final double kP = 0.015, kI = 0.0, kD = 0.0;
    private Drivebase drivebase;

    public TargetAlignment(Drivebase drivebase, Limelight limelight, NavX navX)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> navX.getHeading().getDegrees(),
            () -> navX.getHeading().getDegrees() - limelight.getX(),
            output -> drivebase.set(ControlMode.Throttle, -output, output),
            drivebase
        );

        this.drivebase = drivebase;
    }

    public TargetAlignment()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), Robot.getInstance().getNavX());
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
    }
}
