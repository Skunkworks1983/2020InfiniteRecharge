package frc.team1983.services;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team1983.Robot;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.commands.climber.RunClimberDown;
import frc.team1983.commands.climber.RunClimberUp;
import frc.team1983.commands.collectorAndIndexer.*;
import frc.team1983.commands.shooter.SetArticulationPosition;
import frc.team1983.commands.shooter.SetShooter;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

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

    //driving joysticks for... well.. driving
    public static final double JOYSTICK_DEADZONE = 0.15;
    public static final double JOYSTICK_EXPONENT = 1.7;
    public static final double LINEAR_ZONE = 0.4;
    public static final double LINEAR_SLOPE = Math.abs(Math.pow(LINEAR_ZONE, JOYSTICK_EXPONENT) / (LINEAR_ZONE - JOYSTICK_DEADZONE));

    //shooter buttons as of testing (no, we didn't break it yet)
    public static final int ARTICULATION_DOWN = 1;
    public static final int ARTICULATION_UP = 2;
    public static final int SET_SHOOTER = 9;

    //indexer and collector buttons as of testing (also has yet to break)
    public static final int COLLECT_AND_LOAD = 16;
    public static final int UNLOAD_INDEXER_AND_COLLECTOR = 15;

    public static final int MANUAL_INDEXER = 10;
    public static final int LOAD_INDEXER = 21;
    public static final int UNLOAD_INDEXER = 20;
    public static final int INTERNAL_INDEXER_LOAD = 22;

    public static final int SET_COLLECTOR_DOWN = 7;
    public static final int SET_COLLECTOR_UP = 8;
    public static final int SET_ROLLER = 11;
    protected static final int UNLOAD_COLLECTOR = 23;


    //climber buttons as of testing
    public static final int CLIMBER_UP = 4;
    public static final int CLIMBER_DOWN = 6;

    private double collectorValue = 0.5;
    private double indexerValue = 0.8;
    private double internalIndexerValue = 0.5;
    private double acceleratorValue = 0.9;
    private double flywheelValue = 0.9;

    private double delaySeconds = 0.1;

    private Joystick left, right, panel, operator;
    private HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons;

    protected static double scale(double raw)
    {
        if(Math.abs(raw) < JOYSTICK_DEADZONE) return 0;
        if(Math.abs(raw) < LINEAR_ZONE) return (LINEAR_SLOPE * raw) - (Math.signum(raw) * JOYSTICK_DEADZONE);
        else return Math.pow(Math.abs(raw), JOYSTICK_EXPONENT) * Math.signum(raw);
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
            return left.getX();
        }
        else
        {
            return operator.getX();
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
        getButton(Joysticks.PANEL, SET_SHOOTER).whenHeld(new SetShooter(ControlMode.Throttle,
                acceleratorValue, flywheelValue));

        getButton(Joysticks.PANEL, MANUAL_INDEXER).whenHeld(new ManualIndexer(ControlMode.Throttle,
                indexerValue, internalIndexerValue));

        getButton(Joysticks.PANEL, LOAD_INDEXER).whenHeld(new LoadIndexerTele(Robot.getInstance().getIndexer(),
                Robot.getInstance().getOI(), indexerValue));

        getButton(Joysticks.PANEL, UNLOAD_INDEXER).whenHeld(new UnloadIndexer(ControlMode.Throttle,
                -indexerValue, -internalIndexerValue));

        getButton(Joysticks.PANEL, SET_COLLECTOR_DOWN).whenPressed(new SetCollectorPosition(false));

        getButton(Joysticks.PANEL, SET_COLLECTOR_UP).whenPressed(new SetCollectorPosition(true));

        getButton(Joysticks.PANEL, SET_ROLLER).whenHeld(new SetRollerThrottle(Robot.getInstance().getCollector(),
                collectorValue));

        getButton(Joysticks.PANEL, UNLOAD_COLLECTOR).whenHeld(new UnloadCollector(ControlMode.Throttle,
                -collectorValue));

        getButton(Joysticks.PANEL, COLLECT_AND_LOAD).whenHeld(new CollectAndLoadTele(Robot.getInstance().getCollector(),
                Robot.getInstance().getIndexer(), Robot.getInstance().getOI(), collectorValue, internalIndexerValue,
                indexerValue, delaySeconds));

        getButton(Joysticks.PANEL, UNLOAD_INDEXER_AND_COLLECTOR).whenHeld(new UnloadIndexerAndCollector(
                Robot.getInstance().getCollector(), Robot.getInstance().getIndexer(), ControlMode.Throttle,
                -collectorValue, -indexerValue, -internalIndexerValue));

        getButton(Joysticks.PANEL, CLIMBER_UP).whenPressed(new RunClimberUp());

        getButton(Joysticks.PANEL, CLIMBER_DOWN).whenPressed(new RunClimberDown());

        getButton(Joysticks.OPERATOR, 1).whenHeld(new SetArticulationPosition(Shooter.LOWER_LIMIT));

        getButton(Joysticks.OPERATOR, 2).whenHeld(new TargetAlignment());

        getButton(Joysticks.PANEL, INTERNAL_INDEXER_LOAD).whenHeld(new InternalIndexer(Robot.getInstance().getIndexer(),
                ControlMode.Throttle, internalIndexerValue));
    }
}
