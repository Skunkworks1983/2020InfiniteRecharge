package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Limelight;

public class TargetAlignment extends PIDCommand
{
    private static final double kP = 0.015, kI = 0.0, kD = 0.0;
    private Drivebase drivebase;
    private Limelight limelight;

    public TargetAlignment(Drivebase drivebase, Limelight limelight)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> drivebase.getHeading().getDegrees(),
            () -> drivebase.getHeading().getDegrees() - limelight.getX(),
            output -> drivebase.set(ControlMode.Throttle, -output, output),
            drivebase
        );
        this.drivebase = drivebase;
        this.limelight = limelight;

    }

    public TargetAlignment()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight());
    }

    @Override
    public void initialize()
    {
        super.initialize();
        limelight.setLedMode(Limelight.LedMode.FORCE_ON);
    }

    @Override
    public void execute()
    {
        super.execute();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
        limelight.setLedMode(Limelight.DEFAULT_LED_MODE);
    }
}
