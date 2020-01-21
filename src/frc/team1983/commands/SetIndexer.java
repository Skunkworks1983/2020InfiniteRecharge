package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Indexer;
import frc.team1983.util.motors.ControlMode;

public class SetIndexer extends CommandBase
{
	private Indexer indexer;
	private ControlMode controlMode;
	private double value;

	public SetIndexer(Indexer indexer, ControlMode controlMode, double value)
	{
		this.indexer = indexer;
		this.controlMode = controlMode;
		this.value = value;
	}

	public SetIndexer(ControlMode controlMode, double value)
	{
		this(Robot.getInstance().getIndexer(), controlMode, value);
	}

	@Override
	public void execute()
	{
		indexer.set(controlMode, value);
	}

	@Override
	public void end(boolean interrupted)
	{
		indexer.set(ControlMode.Throttle, 0.0);
	}
}