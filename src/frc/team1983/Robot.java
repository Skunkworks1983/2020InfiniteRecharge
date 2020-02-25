package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.commands.RunTankDrive;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.ColorSensor;
import frc.team1983.util.sensors.Limelight;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.util.sensors.NavX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{
	private static Robot instance;

	private Drivebase drivebase;
	private Limelight limelight;
	private NavX navX;
	private ColorSensor colorSensor;
	private OI oi;

	private UsbCamera camera;

	Robot()
	{
		instance = this;

		limelight = new Limelight();

		drivebase = new Drivebase();
		navX = new NavX();

		colorSensor = new ColorSensor();
		new Thread(colorSensor).start();

		oi = new OI();
		oi.initializeBindings();

		drivebase.setDefaultCommand(new RunGyroDrive());
	}

	@Override
	public void robotInit()
	{
		navX.reset();
		// On GRIP, connect to http://roborio-1983-frc.local:1181/?action=stream
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		limelight.setLedMode(Limelight.DEFAULT_LED_MODE);
	}

	@Override
	public void robotPeriodic()
	{
		SmartDashboard.putNumber("Red", colorSensor.getRed());
		SmartDashboard.putNumber("Green", colorSensor.getGreen());
		SmartDashboard.putNumber("Blue", colorSensor.getBlue());
		SmartDashboard.putNumber("Confidence", colorSensor.getConfidence());
		SmartDashboard.putString("Detected Color", colorSensor.getColor());
	}

	@Override
	public void autonomousInit()
	{
		navX.reset();
		CommandScheduler.getInstance().cancelAll();
		new TargetAlignment().schedule();
	}

	@Override
	public void autonomousPeriodic()
	{

	}

	@Override
	public void teleopInit()
	{
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
