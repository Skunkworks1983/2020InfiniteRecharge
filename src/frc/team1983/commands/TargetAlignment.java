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
            limelight::getX,
            0.0,
            output -> drivebase.set(ControlMode.Throttle, -output, output)
        );

        this.drivebase = drivebase;
        this.limelight = limelight;
    }

    public TargetAlignment()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight());
    }

    @Override
    public void execute()
    {
        super.execute();
        System.out.println(limelight.getX());
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);

        System.out.println("END");
    }

    @Override
    public boolean isFinished()
    {
        return getController().atSetpoint();
    }
}
