package frc.team1983.services;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team1983.commands.controlpanel.PositionControl;
import frc.team1983.commands.controlpanel.RotationControl;
import frc.team1983.commands.controlpanel.ToggleControlPanel;
import frc.team1983.util.sensors.ColorSensor;
import frc.team1983.Robot;
import frc.team1983.autonomous.DriveStraight;
import frc.team1983.commands.climber.RunClimberDown;
import frc.team1983.commands.climber.RunClimberUp;
import frc.team1983.commands.collectorAndIndexer.*;
import frc.team1983.commands.shooter.SetArticulationPosition;
import frc.team1983.commands.shooter.SetShooter;
import frc.team1983.subsystems.Shooter;

import java.awt.*;
import java.util.HashMap;

public class OI
{
    public enum Joysticks
        {
            LEFT(0),
            RIGHT(1),
            PANEL(2),
            OPERATOR(3);

            private int port;

            Joysticks(int port)
            {
                this.port = port;
            }

            public int getPort()
            {
                return port;
        }
    }

    public static final double JOYSTICK_DEADZONE = 0.15;
    public static final double POT_LOWER_DEADZONE = 0.8;
    public static final double POT_UPPER_DEADZONE = -0.1;
    public static final double JOYSTICK_EXPONENT = 1.7;
    public static final double LINEAR_ZONE = 0.4;
    public static final double LINEAR_SLOPE = Math.abs(Math.pow(LINEAR_ZONE, JOYSTICK_EXPONENT) / (LINEAR_ZONE - JOYSTICK_DEADZONE));
 
    public static final int RAISE_CONTROL_PANEL = 4;
    public static final int LOWER_CONTROL_PANEL = 5;

    public static final int INITIALIZE_ROTATION_CONTROL = 1;
    public static final int INITIALIZE_POSITION_CONTROL = 2;
    
    public static final int COLLECT_AND_LOAD = 15;
    public static final int COLLECTOR_FORWARD = 14;

    public static final int COLLECTOR_REVERSE = 13;
    public static final int COLLECTOR_TRANSFER_REVERSE = 11;
    public static final int INTERNAL_INDEXER_REVERSE = 10;
//    public static final int SHOOTER_TRANSFER_REVERSE = 9;
    public static final int REVERSE_ALL = 9;

    public static final int SET_COLLECTOR_POSITION = 12;

    public static final int CLIMBER_UP = 6;
    public static final int CLIMBER_DOWN = 7;

    private double collectorValue = 0.6;

    private Joystick left, right, panel, operator;
    private HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons;

    protected static double scale(double raw)
    {
        if(Math.abs(raw) < JOYSTICK_DEADZONE) return 0;
        if(Math.abs(raw) < LINEAR_ZONE) return (LINEAR_SLOPE * raw) - (Math.signum(raw) * JOYSTICK_DEADZONE);
        else return Math.pow(Math.abs(raw), JOYSTICK_EXPONENT) * Math.signum(raw);
    }

    protected static double PotScale(double raw)
    {
        if(raw > POT_LOWER_DEADZONE)
            return 0;
        if(raw < POT_UPPER_DEADZONE)
            return 1;
        else return (Math.abs(raw) + 1) / 2;
    }

    public OI(Joystick left, Joystick right, Joystick panel, Joystick operator, HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons)
    {
        this.left = left;
        this.right = right;
        this.panel = panel;
        this.operator = operator;
        this.buttons = buttons;
    }

    public OI()
    {
        this(new Joystick(Joysticks.LEFT.getPort()),
                new Joystick(Joysticks.RIGHT.getPort()),
                new Joystick(Joysticks.PANEL.getPort()),
                new Joystick(Joysticks.OPERATOR.getPort()),
                new HashMap<>()
        );
    }

    public double getLeftY()
    {
        return scale(-left.getY());
    }

    public double getRightY()
    {
        return scale(-right.getY());
    }

    public double getRightX()
    {
        return scale(right.getX());
    }

    public double getLeftX()
    {
        if(Math.abs(left.getX()) > 0.15)
        {
            return scale(left.getX());
        }
        else
        {
            return scale(operator.getX());
        }
    }

    public double getOperatorX()
    {

        return operator.getX();
    }

    public double getOperatorY()
    {

        return scale(operator.getY());
    }

    public double getPanelY()
    {
        return PotScale(panel.getY());
    }

    public JoystickButton getButton(Joysticks joystickPort, int button)
    {
        Joystick joystick;
        switch(joystickPort)
        {
            case LEFT:
                joystick = left;
                break;
            case RIGHT:
                joystick = right;
                break;
            case OPERATOR:
                joystick = operator;
                break;
            default: // If it wasn't the other two it must be panel. Java doesn't like it if we just do case PANEL.
                joystick = panel;
                break;
        }

        if(!buttons.containsKey(joystickPort))
            buttons.put(joystickPort, new HashMap<>());
        if(!buttons.get(joystickPort).containsKey(button))
            buttons.get(joystickPort).put(button, new JoystickButton(joystick, button));

        return buttons.get(joystickPort).get(button);
    }
    public void initializeBindings()
    {
        getButton(Joysticks.RIGHT, RAISE_CONTROL_PANEL).whenPressed(new ToggleControlPanel(true));
        getButton(Joysticks.RIGHT, LOWER_CONTROL_PANEL).whenPressed(new ToggleControlPanel(false));
        getButton(Joysticks.RIGHT, INITIALIZE_ROTATION_CONTROL).whenPressed(new RotationControl());
        getButton(Joysticks.RIGHT, INITIALIZE_POSITION_CONTROL).whenPressed(new PositionControl(ColorSensor.ColorEnum.RED));
        
        //Just collector Forward
        getButton(Joysticks.PANEL, COLLECTOR_FORWARD).whenHeld(new SetRollerThrottle(Robot.getInstance().getCollector(),
            collectorValue));

        //Just collector Reverse
        getButton(Joysticks.PANEL, COLLECTOR_REVERSE).whenHeld(new SetRollerThrottle(Robot.getInstance().getCollector(),
                -collectorValue));

        //Full collection and loading
        getButton(Joysticks.PANEL, COLLECT_AND_LOAD).whenHeld(new LoadIndexerTele());

        getButton(Joysticks.OPERATOR, 1).whenHeld(new IndexerStartupTele());

        //Toggle collector
        getButton(Joysticks.PANEL, SET_COLLECTOR_POSITION).whenPressed(new SetCollectorPosition());

        //Run collector transfer in reverse
        getButton(Joysticks.PANEL, COLLECTOR_TRANSFER_REVERSE).whenHeld(new ManualIndexer(-0.15,0,
            0));

        //Run internal indexer in reverse
        getButton(Joysticks.PANEL, INTERNAL_INDEXER_REVERSE).whenHeld(new ManualIndexer(0,-0.15,0));

        //All of indexer in reverse
        getButton(Joysticks.PANEL, REVERSE_ALL).whenHeld(new ManualIndexer(-0.15,-0.15,-0.15));

        //Climber up
        getButton(Joysticks.PANEL, CLIMBER_UP).whenPressed(new RunClimberUp());

        //Climber down
        getButton(Joysticks.PANEL, CLIMBER_DOWN).whenPressed(new RunClimberDown());

        //Hood setpoint for shooting from trench
        getButton(Joysticks.OPERATOR, 8).whenHeld(new SetArticulationPosition(Shooter.TRENCH));

        //Hood setpoint for shooting from protected zone in front of port
        getButton(Joysticks.OPERATOR, 10).whenHeld(new SetArticulationPosition(Shooter.UPPER_LIMIT));

        //Set shooter speed
        getButton(Joysticks.OPERATOR, 2).whenHeld(new SetShooter(0.9, 0.9));

        //Drive back from power port
        getButton(Joysticks.RIGHT, 1).whenHeld(new DriveStraight(-0.75, Units.feetToMeters(2.0)));
    }
}
