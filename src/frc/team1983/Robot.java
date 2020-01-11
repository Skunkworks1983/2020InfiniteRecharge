package frc.team1983;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunTankDrive;
import frc.team1983.subsystems.Drivebase;

public class Robot extends TimedRobot
{
	private Drivebase drivebase;
	private OI oi;

	@Override
	public void robotInit()
	{
		drivebase = new Drivebase();
		oi = new OI();
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

		System.out.println("CommandScheduler: " + CommandScheduler.getInstance());
		System.out.println("Drivebase: " + drivebase);
		System.out.println("OI: " + oi);

		RunTankDrive runTankDrive = new RunTankDrive(drivebase, oi);
		System.out.println("RunTankDrive: " + runTankDrive);
		CommandScheduler.getInstance().schedule(runTankDrive);
	}

	@Override
	public void teleopPeriodic()
	{

	}

	@Override
	public void testPeriodic()
	{

	}

	public Drivebase getDrivebase()
	{
		return drivebase;
	}

	public OI getOi()
	{
		return oi;
	}
}
