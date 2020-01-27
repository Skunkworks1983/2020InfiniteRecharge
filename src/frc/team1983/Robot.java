package frc.team1983;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.autonomous.routines.DoNothing;
import frc.team1983.autonomous.routines.RendezvousToTrench;
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

	private SendableChooser<Pose2d> startingPoseChooser;
	private SendableChooser<Command> autoChooser;

	Robot()
	{
		instance = this;

		navX = new NavX();
		drivebase = new Drivebase();
		drivebase.zero();
		drivebase.setBrake(true); // TODO: remove

		oi = new OI();
		oi.initializeBindings();
	}

	@Override
	public void robotInit()
	{
		startingPoseChooser = new SendableChooser<>();
		startingPoseChooser.setDefaultOption("In Front of Trench Run", Constants.Pose.START_IN_FRONT_OF_TRENCH_RUN);
		startingPoseChooser.addOption("In Front of Power Port", Constants.Pose.START_IN_FRONT_OF_POWER_PORT);
		SmartDashboard.putData("Starting pose chooser", startingPoseChooser);

		autoChooser = new SendableChooser<>();
		autoChooser.setDefaultOption("DO NOT RUN AUTO", new DoNothing());
		autoChooser.addOption("Rendezvous To Trench", new RendezvousToTrench());
		SmartDashboard.putData("Auto chooser", autoChooser);
	}

	@Override
	public void robotPeriodic()
	{
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		drivebase.setPose(startingPoseChooser.getSelected());

		CommandScheduler.getInstance().cancelAll();
		autoChooser.getSelected().schedule();
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
		System.out.println(drivebase.getPose());
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
}
