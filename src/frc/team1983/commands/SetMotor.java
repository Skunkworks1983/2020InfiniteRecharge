package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Motor;

public class SetMotor extends CommandBase
{
	private Motor motor;
	private ControlMode controlMode;
	private double value;

	public SetMotor(Motor motor, ControlMode controlMode, double value)
	{
		this.motor = motor;
		this.controlMode = controlMode;
		this.value = value;
	}

	@Override
	public void execute()
	{
		motor.set(controlMode, value);
	}

	@Override
	public void end(boolean interrupted)
	{
		motor.set(ControlMode.Throttle, 0.0);
	}
}