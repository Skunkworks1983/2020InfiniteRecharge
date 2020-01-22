package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetAccelerator extends CommandBase
{
	private Shooter shooter;
	private ControlMode controlMode;
	private double value;

	public SetAccelerator(Shooter shooter, ControlMode controlMode, double value)
	{
		this.shooter = shooter;
		this.controlMode = controlMode;
		this.value = value;
	}

	public SetAccelerator(ControlMode controlMode, double value)
	{
		this(Robot.getInstance().getShooter(), controlMode, value);
	}

	@Override
	public void execute()
	{
		shooter.setAccelerator(controlMode, value);
	}

	@Override
	public void end(boolean interrupted)
	{
		shooter.setAccelerator(ControlMode.Throttle, 0.0);
	}
}