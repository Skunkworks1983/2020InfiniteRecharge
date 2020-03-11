package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;

public class TurnAngle extends PIDCommand
{
    private static final double kP = 0.011, kI = 0.05, kD = 0.0005, kF = 0.04;
    private static final double MAX_OUTPUT = 0.5;
    private static final double INTEGRATOR_RANGE = 0.4;
    private static final double INTEGRATOR_ENABLED_THRESHOLD = 7.0; // degrees
    private Drivebase drivebase;

    private double previousError;

    public TurnAngle(Drivebase drivebase, double targetAngle)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> drivebase.getHeading().getDegrees(),
            () -> targetAngle,
            output -> {
                double feedforward = kF * Math.signum(output);
                drivebase.set(ControlMode.Throttle,
                    MathUtil.clamp(-output - feedforward, -MAX_OUTPUT, MAX_OUTPUT),
                    MathUtil.clamp(output + feedforward, -MAX_OUTPUT, MAX_OUTPUT)
                );
            },
            drivebase
        );

        this.drivebase = drivebase;

        getController().enableContinuousInput(-180, 180);
        getController().setIntegratorRange(-INTEGRATOR_RANGE, INTEGRATOR_RANGE);

        addRequirements(drivebase);
    }

    public TurnAngle(double targetAngle)
    {
        this(Robot.getInstance().getDrivebase(), targetAngle);
    }

    @Override
    public void execute()
    {
        if(Math.signum(previousError) != Math.signum(getController().getPositionError()))
        {
            getController().reset();
        }

        if(Math.abs(getController().getPositionError()) > INTEGRATOR_ENABLED_THRESHOLD)
        {
            getController().setI(0);
        }
        else
        {
            getController().setI(kI);
        }

        super.execute();
        previousError = getController().getPositionError();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
    }

    @Override
    public boolean isFinished()
    {
        return getController().atSetpoint();
    }
}