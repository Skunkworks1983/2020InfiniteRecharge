package frc.team1983;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team1983.commands.RunGyroDrive;
import frc.team1983.commands.shooter.ManualShooter;
import frc.team1983.commands.shooter.SetArticulation;
import frc.team1983.services.OI;
import frc.team1983.subsystems.*;
import frc.team1983.util.sensors.Limelight;
import frc.team1983.util.sensors.NavX;

public class Robot extends TimedRobot
{
    private static Robot instance;

    private Drivebase drivebase;
    private Shooter shooter;
    private Limelight limelight;
    private NavX navX;
    private Collector collector;
    private OI oi;
    private Climber climber;
    private Indexer indexer;

    private UsbCamera camera;

    Robot()
    {
        instance = this;

        drivebase = new Drivebase();
        navX = new NavX();
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
        navX.reset();
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
        navX.reset();
        CommandScheduler.getInstance().cancelAll();
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

    public NavX getNavX()
    {
        return navX;
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
