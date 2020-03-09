package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetShooter extends CommandBase
{
    private Shooter shooter;

    private double acceleratorThrottle;
    private double flywheelThrottle;

    private static final double KP = 0.1 / 125, KF = 0.9 / (8000. * (18./30));
    private static final double CONVERSION = 0.9 / 4800;
    private static final double MAXIMUM_DECELERATION = 0.05;

    private double lastFlywheelOutput;
    private double lastAcceleratorOutput;


    public SetShooter(Shooter shooter, double acceleratorThrottle, double flywheelThrottle)
    {
        this.shooter = shooter;
        this.acceleratorThrottle = acceleratorThrottle;
        this.flywheelThrottle = flywheelThrottle;
    }

    public SetShooter(double acceleratorThrottle, double flywheelVelocity)
    {
        this(Robot.getInstance().getShooter(),  acceleratorThrottle, flywheelVelocity);
    }

    @Override
    public void initialize()
    {
        shooter.setVoltageRamp(shooter.voltageRamp);
    }

    @Override
    public void execute()
    {
        double acceleratorError = (acceleratorThrottle / CONVERSION) - shooter.getAcceleratorVelocity();
        double acceleratorValue = KP * acceleratorError + KF * (acceleratorThrottle / CONVERSION);

        double FlywheelError = (flywheelThrottle / CONVERSION) - shooter.getFlywheelVelocity();
        double flywheelValue = KP * FlywheelError + KF * (flywheelThrottle / CONVERSION);

        if (acceleratorValue < lastAcceleratorOutput && Math.abs(acceleratorValue - lastAcceleratorOutput) > MAXIMUM_DECELERATION)
        {
            acceleratorValue = lastAcceleratorOutput - MAXIMUM_DECELERATION;
        }
        lastAcceleratorOutput = acceleratorValue;

        if (flywheelValue < lastFlywheelOutput && Math.abs(flywheelValue - lastFlywheelOutput) > MAXIMUM_DECELERATION)
        {
            flywheelValue = lastFlywheelOutput - MAXIMUM_DECELERATION;
        }
        lastFlywheelOutput = flywheelValue;

        shooter.set(ControlMode.Throttle, acceleratorValue, flywheelValue);
        SmartDashboard.putNumber("Accelerator Velocity", Robot.getInstance().getShooter().getAcceleratorVelocity() * 30 / 18.0);
        SmartDashboard.putNumber("Flywheel Velocity", Robot.getInstance().getShooter().getFlywheelVelocity() * 30 / 18.0);
    }

    @Override
    public void end(boolean interrupted)
    {
        shooter.set(ControlMode.Throttle, 0.0, 0.0);
    }
}
