package frc.team1983;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.subsystems.Indexer;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.sensors.NavX;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private Collector collector;
	private Indexer indexer;
	private Shooter shooter;
	private NavX navX;
	private OI oi;

	Robot()
	{
		instance = this;

		navX = new NavX();
		drivebase = new Drivebase();
		collector = new Collector();
		indexer = new Indexer();
		shooter = new Shooter();

		SmartDashboard.putNumber("Collector Intake Throttle", 0.5);
		SmartDashboard.putNumber("Collector Expel Throttle", 0.5);

		SmartDashboard.putNumber("Indexer Intake Throttle", 0.5);
		SmartDashboard.putNumber("Indexer Expel Throttle", 0.5);

		SmartDashboard.putNumber("Accelerator Intake Throttle", 0.5);
		SmartDashboard.putNumber("Accelerator Expel Throttle", 0.5);

		SmartDashboard.putNumber("Flywheel Throttle", 0.5);


		oi = new OI();
		oi.initializeBindings();
	}

	@Override
	public void robotInit()
	{

	}

	@Override
	public void robotPeriodic()
	{
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void autonomousPeriodic()
	{

	}

	@Override
	public void teleopInit()
	{
		CommandScheduler.getInstance().cancelAll();
		new RunGyroDrive().schedule();
	}

	@Override
	public void teleopPeriodic()
	{

	}

	@Override
	public void testPeriodic()
	{

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

	public Collector getCollector()
	{
		return collector;
	}

	public Indexer getIndexer()
	{
		return indexer;
	}

	public Shooter getShooter()
	{
		return shooter;
	}

	public NavX getNavX()
	{
		return navX;
	}

	public OI getOI()
	{
		return oi;
	}
}
