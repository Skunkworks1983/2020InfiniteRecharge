package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Motor;

public class SetMotor extends InstantCommand
{
	public SetMotor(Motor motor, ControlMode controlMode, double value)
	{
		super(() -> motor.set(controlMode, value));
	}
}
