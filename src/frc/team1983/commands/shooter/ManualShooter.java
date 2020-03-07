package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class ManualShooter extends CommandBase
{
    private Shooter shooter;
    private OI oi;

    public ManualShooter(Shooter shooter, OI oi)
    {
        this.shooter = shooter;
        this.oi = oi;
    }

    public ManualShooter()
    {
        this(Robot.getInstance().getShooter(), Robot.getInstance().getOI());
    }

    @Override
    public void initialize()
    {
        shooter.setVoltageRamp(shooter.voltageRamp);
    }

    @Override
    public void execute()
    {
        double throttle = oi.getPanelY();
        shooter.set(ControlMode.Throttle, throttle, throttle);
        SmartDashboard.putNumber("Accelerator Velocity", Robot.getInstance().getShooter().getAcceleratorVelocity() * 30 / 18.0);
        SmartDashboard.putNumber("Flywheel Velocity", Robot.getInstance().getShooter().getFlywheelVelocity() * 30 / 18.0);
    }
}
