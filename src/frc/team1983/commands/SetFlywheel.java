package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetFlywheel extends CommandBase
{
	private Shooter shooter;
	private ControlMode controlMode;
	private double value;

	public SetFlywheel(Shooter shooter, ControlMode controlMode, double value)
	{
		this.shooter = shooter;
		this.controlMode = controlMode;
		this.value = value;
	}

	public SetFlywheel(ControlMode controlMode, double value)
	{
		this(Robot.getInstance().getShooter(), controlMode, value);
	}

	@Override
	public void execute()
	{
		shooter.setFlywheel(controlMode, value);
	}

	@Override
	public void end(boolean interrupted)
	{
		shooter.setFlywheel(ControlMode.Throttle, 0.0);
	}
}