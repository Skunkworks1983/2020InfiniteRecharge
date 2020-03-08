package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.commands.shooter.SetArticulation;
import frc.team1983.services.OI;
import frc.team1983.subsystems.*;
import frc.team1983.util.sensors.Limelight;

public class Robot extends TimedRobot
{
    private static Robot instance;

    private Drivebase drivebase;
    private Shooter shooter;
    private Limelight limelight;
    private Collector collector;
    private OI oi;
    private Climber climber;
    private Indexer indexer;

    private UsbCamera cameraAim, cameraCollect;

    Robot()
    {
        instance = this;

        drivebase = new Drivebase();
        collector = new Collector();
        indexer = new Indexer();

        climber = new Climber();

        shooter = new Shooter();
        limelight = new Limelight();
        oi = new OI();
        oi.initializeBindings();

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
        System.out.println(oi.getOperatorSlider());
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
        CommandScheduler.getInstance().cancelAll();
        new RunGyroDrive().schedule();
        new SetArticulation().schedule();
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
}
