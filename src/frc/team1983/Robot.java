package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team1983.autonomous.Auto;
import frc.team1983.autonomous.AutoFactory;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.constants.Constants;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.Limelight;
import frc.team1983.util.sensors.NavX;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private Limelight limelight;
	private NavX navX;
	private OI oi;

	private UsbCamera camera;

	private SendableChooser<Pose2d> startingPoseChooser;
	private SendableChooser<Auto> autoChooser;

	Robot()
	{
		instance = this;

		navX = new NavX();
		limelight = new Limelight();

		drivebase = new Drivebase();
		drivebase.zero();
		drivebase.setBrake(false);

		oi = new OI();
		oi.initializeBindings();

		drivebase.setDefaultCommand(new RunGyroDrive());
	}

	@Override
	public void robotInit()
	{
		SmartDashboard.putNumber("Wait Time", 0.0);

		startingPoseChooser = new SendableChooser<>();
		startingPoseChooser.setDefaultOption("In Front of Trench Run", Constants.Pose.START_IN_FRONT_OF_TRENCH_RUN);
		startingPoseChooser.addOption("In Front of Power Port", Constants.Pose.START_IN_FRONT_OF_POWER_PORT);
		startingPoseChooser.addOption("In Front of Opponent Trench Run", Constants.Pose.START_IN_FRONT_OF_OPPONENT_TRENCH_RUN);
		SmartDashboard.putData("Starting pose chooser", startingPoseChooser);

		autoChooser = new SendableChooser<>();
		autoChooser.setDefaultOption("DO NOT RUN AUTO", Auto.DO_NOTHING);
		autoChooser.addOption("In Front of Trench Run to Rendezvous Point to Trench Run", Auto.IN_FRONT_OF_TRENCH_RUN_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN);
		autoChooser.addOption("In Front of Opponent Trench Run to Opponent Trench Run to Rendezvous Point", Auto.IN_FRONT_OF_OPPONENT_TRENCH_RUN_TO_OPPONENT_TRENCH_RUN_TO_RENDEZVOUS_POINT);
		SmartDashboard.putData("Auto chooser", autoChooser);

		// On GRIP, connect to http://roborio-1983-frc.local:1181/?action=stream
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
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
		drivebase.setBrake(true);

		CommandScheduler.getInstance().cancelAll();
		new SequentialCommandGroup(
			new WaitCommand(SmartDashboard.getNumber("Wait Time", 0.0)),
			AutoFactory.getAuto(autoChooser.getSelected())
		).schedule();
	}

	@Override
	public void autonomousPeriodic()
	{

	}

	@Override
	public void teleopInit()
	{
		drivebase.setBrake(false);

		navX.reset();
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

	public Limelight getLimelight()
	{
		return limelight;
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
