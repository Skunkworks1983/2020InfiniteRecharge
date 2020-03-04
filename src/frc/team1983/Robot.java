package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.controlpanel.PollFMS;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.commands.controlpanel.RotationControl;
import frc.team1983.services.OI;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.ColorSensor;
import frc.team1983.util.sensors.Limelight;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private ControlPanel controlPanel;
	private Limelight limelight;
	private ColorSensor colorSensor;
	private OI oi;
	
	double minHue = 360;
	double maxHue = 0;
	
	double minSaturation = 20;
	double maxSaturation = 0;
	
	double minValue = 20;
	double maxValue = 0;
	
	private UsbCamera camera;

	Robot()
	{
		instance = this;

		limelight = new Limelight();

		drivebase = new Drivebase();

		colorSensor = new ColorSensor();

		oi = new OI();
		oi.initializeBindings();

		controlPanel = new ControlPanel();

		drivebase.setDefaultCommand(new RunGyroDrive());
	}

	@Override
	public void robotInit()
	{
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
		drivebase.resetHeading();
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void autonomousPeriodic()
	{

	}

	@Override
	public void teleopInit()
	{
		drivebase.resetHeading();
		CommandScheduler.getInstance().cancelAll();
		new RunGyroDrive().schedule();
		
		
	}

	@Override
	public void teleopPeriodic()
	{
		if (!controlPanel.isAlreadyPolled())
		{
			new PollFMS().schedule();
		}
	}
	
	@Override
	public void testInit()
	{
		CommandScheduler.getInstance().cancelAll();
		new RotationControl().schedule();
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

	public ControlPanel getControlPanel()
	{
		return controlPanel;
	}

	public Limelight getLimelight()
	{
		return limelight;
	}

	public ColorSensor getColorSensor()
	{
		return colorSensor;
	}

	public OI getOI()
	{
		return oi;
	}
}
