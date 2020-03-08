package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetShooter extends CommandBase
{
    private Shooter shooter;
    private OI oi;
    private double acceleratorValue;
    private double flywheelVelocity;

    private static double KP = 0, KF = 0.9 / (8000 * (18/30));
    private static double maximumDeceleration = 0.05;

    private double lastOutput;


    public SetShooter(Shooter shooter, OI oi, double acceleratorValue, double flywheelVelocity)
    {
        this.shooter = shooter;
        this.oi = oi;
        this.acceleratorValue = acceleratorValue;
        this.flywheelVelocity = flywheelVelocity;
    }

    public SetShooter(double acceleratorValue, double flywheelVelocity)
    {
        this(Robot.getInstance().getShooter(), Robot.getInstance().getOI(), acceleratorValue, flywheelVelocity);
    }

    @Override
    public void initialize()
    {
        shooter.setVoltageRamp(shooter.voltageRamp);
    }

    @Override
    public void execute()
    {
        double error = flywheelVelocity - shooter.getFlywheelVelocity();
        double flyWheelValue = KP * error + KF * flywheelVelocity;

        if (flyWheelValue < lastOutput && Math.abs(flyWheelValue - lastOutput) > maximumDeceleration)
        {
            flyWheelValue = lastOutput - maximumDeceleration;
        }
        lastOutput = flyWheelValue;

        shooter.set(ControlMode.Throttle, acceleratorValue, flyWheelValue + oi.getPanelY());
        SmartDashboard.putNumber("Accelerator Velocity", Robot.getInstance().getShooter().getAcceleratorVelocity() * 30 / 18.0);
        SmartDashboard.putNumber("Flywheel Velocity", Robot.getInstance().getShooter().getFlywheelVelocity() * 30 / 18.0);
    }

    @Override
    public void end(boolean interrupted)
    {
        shooter.set(ControlMode.Throttle, 0.0, 0.0);
    }
}
