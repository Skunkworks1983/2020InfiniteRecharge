package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.controlpanel.PollFMS;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.commands.controlpanel.RotationControl;
import frc.team1983.services.OI;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.sensors.ColorSensor;
import frc.team1983.commands.shooter.SetArticulation;
import frc.team1983.subsystems.*;
import frc.team1983.util.sensors.Limelight;

public class Robot extends TimedRobot
{
    private static Robot instance;

	private Drivebase drivebase;
	private ControlPanel controlPanel;
	private Limelight limelight;
	private ColorSensor colorSensor;
	private OI oi;
	private UsbCamera camera;
    private Shooter shooter;
    private Collector collector;
    private Climber climber;
    private Indexer indexer;

    private UsbCamera cameraAim, cameraCollect;

    Robot()
    {
        instance = this;

        drivebase = new Drivebase();
        
        collector = new Collector();
        indexer = new Indexer();
		shooter = new Shooter();
	
		climber = new Climber();

		colorSensor = new ColorSensor();
    
        controlPanel = new ControlPanel();
        
		oi = new OI();
		oi.initializeBindings();
	
		limelight = new Limelight();
		
		drivebase.setDefaultCommand(new RunGyroDrive());
        shooter.setDefaultCommand(new SetArticulation());
    }

    @Override
    public void robotInit()
    {
        // On GRIP, connect to http://roborio-1983-frc.local:1181/?action=stream
        cameraAim = CameraServer.getInstance().startAutomaticCapture();
        cameraCollect = CameraServer.getInstance().startAutomaticCapture();
        cameraAim.setResolution(320, 240);
        cameraCollect.setResolution(320, 240);
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
		new RunGyroDrive().schedule();
	}
	
	@Override
	public void teleopInit()
	{
		CommandScheduler.getInstance().cancelAll();
		new RunGyroDrive().schedule();
		new SetArticulation().schedule();
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

    public Shooter getShooter()
    {
        return shooter;
    }

    public OI getOI()
    {
        return oi;
    }

    public Collector getCollector()
    {
        return collector;
    }

    public Climber getClimber()
    {
        return climber;
    }

    public Indexer getIndexer()
    {
        return indexer;
    }
	
	public ControlPanel getControlPanel()
	{
		return controlPanel;
	}
	
}
