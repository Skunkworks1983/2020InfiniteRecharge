package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.constants.Constants;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Limelight;

public class TargetAlignment extends PIDCommand
{
    private static final double kP = 0.01, kI = 0.0, kD = 0.0, kF = 0.015;
    private static final double MAX_OUTPUT = 0.5;
    private Drivebase drivebase;
    private Limelight limelight;

    private double targetAngle;
    private double setpoint;
    private boolean useTargetAngle = true;

    public TargetAlignment(Drivebase drivebase, Limelight limelight, double targetAngle)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> drivebase.getHeading().getDegrees(),
            () -> targetAngle,
            output -> {
                double feedforward = kF * Math.signum(output);
                Math.max(Math.min(output, MAX_OUTPUT), -MAX_OUTPUT);
                drivebase.set(ControlMode.Throttle, Math.max(Math.min(-output - feedforward, MAX_OUTPUT), -MAX_OUTPUT),
                                                    Math.max(Math.min(output + feedforward, MAX_OUTPUT), -MAX_OUTPUT));
            },
            drivebase
        );

        this.drivebase = drivebase;
        this.limelight = limelight;

        this.targetAngle = targetAngle;
        this.setpoint = targetAngle;

        m_controller.enableContinuousInput(-180, 180);
        addRequirements(drivebase);
    }

    public TargetAlignment(Drivebase drivebase, Limelight limelight, Pose2d pose)
    {
    	this(drivebase, limelight,
    	    180 + Math.toDegrees(Math.atan(
            (pose.getTranslation().getY() - Constants.Pose.PORT_TARGET.getTranslation().getY()) /
            (pose.getTranslation().getX() - Constants.Pose.PORT_TARGET.getTranslation().getX())
        )));
    }

    public TargetAlignment(Drivebase drivebase, Limelight limelight)
    {
    	this(drivebase, limelight, 0.0);
    	 useTargetAngle = false;
    }

    public TargetAlignment(Pose2d pose)
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), pose);
    }

    public TargetAlignment(double targetAngle)
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), targetAngle);
    }

    public TargetAlignment()
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight());
    }

    @Override
    public void initialize()
    {
//        limelight.setLedMode(Limelight.LedMode.FORCE_ON);
        m_setpoint = () -> setpoint;
        super.initialize();
    }

    @Override
    public void execute()
    {
        if(limelight.isTargetDetected())
        {
            setpoint = drivebase.getHeading().getDegrees() - limelight.getX();
        }
        else if(useTargetAngle)
        {
            setpoint = targetAngle;
        }
        else
        {
            setpoint = drivebase.getHeading().getDegrees();
        }

        super.execute();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
        limelight.setLedMode(Limelight.DEFAULT_LED_MODE);
    }

    public double getTargetAngle()
    {
        return targetAngle;
    }
}
