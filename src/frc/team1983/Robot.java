package frc.team1983;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.FollowTrajectory;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.constants.Constants;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.NavX;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private NavX navX;
	private OI oi;

	Robot()
	{
		instance = this;

		navX = new NavX();
		drivebase = new Drivebase();
		drivebase.zero();

		oi = new OI();
		oi.initializeBindings();
	}

	@Override
	public void robotInit()
	{
		zero();
	}

	@Override
	public void robotPeriodic()
	{
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		zero();
		
		drivebase.setPose(Constants.Pose.START);

		CommandScheduler.getInstance().cancelAll();
		new FollowTrajectory(
			true,
			Constants.Pose.START,
			Constants.Pose.RENDEZVOUS_POINT_BALL_1
		).schedule();
	}

	@Override
	public void autonomousPeriodic()
	{
		System.out.println("Pose: " + drivebase.getPose());
	}

	@Override
	public void teleopInit()
	{
		zero();

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

	public NavX getNavX()
	{
		return navX;
	}

	public OI getOI()
	{
		return oi;
	}

	public void zero()
	{
		navX.reset();
		drivebase.zero();
	}
}
