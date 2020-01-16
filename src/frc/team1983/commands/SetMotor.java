package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.util.motors.Motor;

public class SetMotor extends InstantCommand
{
	public SetMotor(Motor motor, double throttle)
	{
		super(() -> motor.set(throttle));
	}
}
