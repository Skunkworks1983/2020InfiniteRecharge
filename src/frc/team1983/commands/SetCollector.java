package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;

public class SetCollector extends CommandBase
{
	private Collector collector;
	private ControlMode controlMode;
	private double value;

	public SetCollector(Collector collector, ControlMode controlMode, double value)
	{
		this.collector = collector;
		this.controlMode = controlMode;
		this.value = value;
	}

	public SetCollector(ControlMode controlMode, double value)
	{
		this(Robot.getInstance().getCollector(), controlMode, value);
	}

	@Override
	public void execute()
	{
		collector.set(controlMode, value);
	}

	@Override
	public void end(boolean interrupted)
	{
		collector.set(ControlMode.Throttle, 0.0);
	}
}