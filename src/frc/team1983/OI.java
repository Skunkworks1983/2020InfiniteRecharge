package frc.team1983;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.util.HashMap;

public class OI
{
    public enum Joysticks
    {
        LEFT(0),
        RIGHT(1),
        PANEL(2);

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

    public static final int JOYSTICK_TRIGGER = 1;
    public static final int JOYSTICK_BOTTOM_BUTTON = 2;
    public static final int JOYSTICK_TOP_BUTTON = 3;
    public static final int JOYSTICK_LEFT_BUTTON = 4;
    public static final int JOYSTICK_RIGHT_BUTTON = 5;

    // TODO: update button names for 2020 OI
    public static final int HATCH_MODE_ENABLED = 14;

    public static final int ELEVATOR_1 = 16;
    public static final int ELEVATOR_2 = 15;
    public static final int ELEVATOR_3 = 12;
    public static final int ELEVATOR_4 = 11;
    public static final int ELEVATOR_5 = 10;
    public static final int ELEVATOR_6 = 9;

    public static final int TOGGLE_COLLECTOR_CLOSED = 8;
    public static final int TOGGLE_COLLECTOR_FOLDED = 7;

    public static final int EXTEND_MANIPULATOR = 23;
    public static final int RETRACT_MANIPULATOR = 20;

    public static final int EXPEL_PANEL = 21;
    public static final int INTAKE_PANEL = 22;

    public static final int EXPEL_BALL = 22;
    public static final int INTAKE_BALL = 21;

    public static final int MANUAL_ENABLED = 24;
    public static final int MANUAL_ELEVATOR_UP = 2;
    public static final int MANUAL_ELEVATOR_DOWN = 1;
    public static final int MANUAL_CLIMB_ELEVATOR_UP = 4;
    public static final int MANUAL_CLIMB_ELEVATOR_DOWN = 3;
    public static final int MANUAL_COLLECTOR_OUT = 5;
    public static final int MANUAL_COLLECTOR_IN = 6;

    public static final int LEVEL_TWO_CLIMB_ENABLED = 25;
    public static final int CLIMB = 13;

    public static final double JOYSTICK_DEADZONE = 0.15;

    private Joystick left, right, panel;
    private HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons;

    public OI(Joystick left, Joystick right, Joystick panel, HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons)
    {
        this.left = left;
        this.right = right;
        this.panel = panel;
        this.buttons = buttons;
    }

    public OI()
    {
        this(new Joystick(Joysticks.LEFT.getPort()),
            new Joystick(Joysticks.RIGHT.getPort()),
            new Joystick(Joysticks.PANEL.getPort()),
            new HashMap<>()
        );
    }

    private double scale(double raw)
    {
        if(Math.abs(raw) < JOYSTICK_DEADZONE)
            return 0;

        return raw > 0 ? raw * raw : -(raw * raw);
    }

    public double getLeftY()
    {
        return scale(-left.getY());
    }

    public double getRightY()
    {
        return scale(-right.getY());
    }

    public double getLeftX()
    {
        return scale(-left.getX());
    }

    public double getRightX()
    {
        return scale(-right.getX());
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
            default:
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

    }
}
