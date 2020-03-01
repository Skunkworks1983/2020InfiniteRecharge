package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetShooter extends CommandBase
{
    private Shooter shooter;
    private ControlMode controlMode;
    private double acceleratorValue, flywheelValue;

    public SetShooter(Shooter shooter, ControlMode controlMode, double acceleratorValue, double flywheelValue)
    {
        this.shooter = shooter;
        this.controlMode = controlMode;
        this.acceleratorValue = acceleratorValue;
        this.flywheelValue = flywheelValue;
    }

    public SetShooter(ControlMode controlMode, double acceleratorValue, double flywheelValue)
    {
        this(Robot.getInstance().getShooter(), controlMode, acceleratorValue, flywheelValue);
    }

    public SetShooter(ControlMode controlMode, double value)
    {
        this(Robot.getInstance().getShooter(), controlMode, value, value);
    }

    @Override
    public void initialize()
    {
        shooter.setVoltageRamp(shooter.voltageRamp);
    }

    @Override
    public void execute()
    {
        shooter.set(controlMode, acceleratorValue, flywheelValue);
        SmartDashboard.putNumber("Accelerator Velocity", Robot.getInstance().getShooter().getAcceleratorVelocity() * 30 / 18.0);
        SmartDashboard.putNumber("Flywheel Velocity", Robot.getInstance().getShooter().getFlywheelVelocity() * 30 / 18.0);
    }

    @Override
    public void end(boolean interrupted)
    {
        shooter.set(ControlMode.Throttle, 0.0, 0.0);
    }
}
