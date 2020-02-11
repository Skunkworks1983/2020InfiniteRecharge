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
    private Limelight limelight;
    private NavX navX;

    private double initialOffset;
    private double setpoint;
    private boolean targetWasDetected = false;

    public TargetAlignment(Drivebase drivebase, Limelight limelight, NavX navX, boolean turnRight)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> {
                System.out.printf("input: %f    ", navX.getHeading().getDegrees());
                return navX.getHeading().getDegrees();
            },
            () -> turnRight ? Limelight.FOV_X / 2.0 : -Limelight.FOV_X / 2.0,
            output -> {
                System.out.printf("output: %f\n", output);
                drivebase.set(ControlMode.Throttle, -output, output);
            },
            drivebase
        );

        this.drivebase = drivebase;
        this.limelight = limelight;
        this.navX = navX;
        this.initialOffset = turnRight ? Limelight.FOV_X / 2.0 : -Limelight.FOV_X / 2.0;
        this.setpoint = initialOffset;

        addRequirements(drivebase);
    }

    public TargetAlignment(boolean turnRight)
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), Robot.getInstance().getNavX(), turnRight);
    }

    @Override
    public void initialize()
    {
        m_setpoint = () -> {
            System.out.printf("setpoint: %f    ", setpoint);
            return setpoint;
        };
        super.initialize();
    }

    @Override
    public void execute()
    {
        if(limelight.isTargetDetected())
        {
            targetWasDetected = true;
            setpoint = navX.getHeading().getDegrees() - limelight.getX();
        }
        else if(!targetWasDetected)
        {
            setpoint = navX.getHeading().getDegrees() - initialOffset;
        }

        // Must be called last because setpoint is never initialized correctly
        super.execute();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
    }
}