package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team1983.autonomous.Auto;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.Limelight;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private Limelight limelight;
	private OI oi;

	private UsbCamera camera;

	private SendableChooser<Auto> autoChooser;
	private SendableChooser<Auto> driveBeforeAutoChooser;

	Robot()
	{
		instance = this;

		limelight = new Limelight();
		limelight.setLedMode(Limelight.DEFAULT_LED_MODE);

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

		autoChooser = new SendableChooser<>();
		autoChooser.setDefaultOption("DO NOT RUN AUTO", Auto.DO_NOTHING);
		autoChooser.addOption("In Front of Trench Run -> Rendezvous Point -> Trench Run", Auto.IN_FRONT_OF_TRENCH_RUN_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN);
		autoChooser.addOption("In Front of Power Port -> Rendezvous Point -> Trench Run", Auto.IN_FRONT_OF_POWER_PORT_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN);
		autoChooser.addOption("In Front of Power Port -> Rendezvous Point", Auto.IN_FRONT_OF_POWER_PORT_TO_RENDEZVOUS_POINT);
		autoChooser.addOption("In Front of Opponent Trench Run -> Opponent Trench Run -> Rendezvous Point", Auto.IN_FRONT_OF_OPPONENT_TRENCH_RUN_TO_OPPONENT_TRENCH_RUN_TO_RENDEZVOUS_POINT);
		autoChooser.addOption("In Front of Trench Run -> Trench Run", Auto.IN_FRONT_OF_TRENCH_RUN_TO_TRENCH_RUN);
		SmartDashboard.putData("Auto chooser", autoChooser);

		driveBeforeAutoChooser = new SendableChooser<>();
		driveBeforeAutoChooser.setDefaultOption("DO NOT DRIVE BEFORE AUTO", Auto.DO_NOTHING);
		driveBeforeAutoChooser.addOption("Drive Forward", Auto.DRIVE_FORWARD);
		driveBeforeAutoChooser.addOption("Drive Backward", Auto.DRIVE_BACKWARD);
		SmartDashboard.putData("Drive before auto chooser", driveBeforeAutoChooser);

		drivebase.resetHeading();

		// On GRIP, connect to http://roborio-1983-frc.local:1181/?action=stream
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		limelight.setLedMode(Limelight.DEFAULT_LED_MODE);
	}

	@Override
	public void robotPeriodic()
	{
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		drivebase.setBrake(false);

		CommandScheduler.getInstance().cancelAll();
		new SequentialCommandGroup(
			new WaitCommand(SmartDashboard.getNumber("Wait Time", 0.0)),
			driveBeforeAutoChooser.getSelected().getAuto(),
			autoChooser.getSelected().getAuto()
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

	@Override
	public void disabledInit()
	{
		drivebase.setBrake(true);
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

	public OI getOI()
	{
		return oi;
	}
}
