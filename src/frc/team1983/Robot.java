package frc.team1983;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.constants.RobotMap;
import frc.team1983.services.OI;
import frc.team1983.subsystems.climber.Elevator;
import frc.team1983.subsystems.drivebase.Drivebase;
import frc.team1983.util.sensors.NavX;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private Elevator elevator;

	private Compressor compressor;
	private NavX navX;
	private OI oi;

	Robot()
	{
		instance = this;

		drivebase = new Drivebase();
		drivebase.zero();

		elevator = new Elevator();
		elevator.zero();

		compressor = new Compressor(RobotMap.COMPRESSOR);

		navX = new NavX();
		navX.reset();

		oi = new OI();
	}

	@Override
	public void robotInit()
	{
		compressor.start();
	}

	@Override
	public void robotPeriodic()
	{
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{

	}

	@Override
	public void autonomousPeriodic()
	{

	}

	@Override
	public void teleopInit()
	{
		CommandScheduler.getInstance().cancelAll();
		new RunGyroDrive(drivebase, oi).schedule();
	}

	@Override
	public void teleopPeriodic()
	{

	}

	@Override
	public void disabledInit()
	{
		compressor.stop();
	}

	public static Robot getInstance()
	{
		if (instance == null)
			instance = new Robot();
		return instance;
	}

	public Drivebase getDrivebase()
	{
		return drivebase;
	}

	public Elevator getElevator()
	{
		return elevator;
	}

	public NavX getNavX()
	{
		return navX;
	}

	public OI getOi()
	{
		return oi;
	}
}