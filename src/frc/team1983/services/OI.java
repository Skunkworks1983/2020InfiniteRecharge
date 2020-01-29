package frc.team1983.services;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team1983.commands.*;
import frc.team1983.constants.Constants;
import frc.team1983.util.motors.ControlMode;

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

    public static final int COLLECT = 22;
    public static final int EXPEL = 21;

    public static final int INDEXER_INTAKE = 23;
    public static final int INDEXER_EXPEL = 20;

    public static final int SHOOT_1 = 19;
    public static final int SHOOT_2 = 18;
    public static final int SHOOT_3 = 17;

    protected static final double JOYSTICK_DEADZONE = 0.15;
    protected static final double JOYSTICK_EXPONENT = 1.7;
    protected static final double LINEAR_ZONE = 0.4;
    protected static final double LINEAR_SLOPE = Math.abs(Math.pow(LINEAR_ZONE, JOYSTICK_EXPONENT) / (LINEAR_ZONE - JOYSTICK_DEADZONE));

    private Joystick left, right, panel;
    private HashMap<Joysticks, HashMap<Integer, JoystickButton>> buttons;

    protected static double scale(double raw)
    {
        if(Math.abs(raw) < JOYSTICK_DEADZONE) return 0;
        if(Math.abs(raw) < LINEAR_ZONE) return (LINEAR_SLOPE * raw) - (Math.signum(raw) * JOYSTICK_DEADZONE);
        else return Math.pow(Math.abs(raw), JOYSTICK_EXPONENT) * Math.signum(raw);
    }

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
        return left.getX();
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
        getButton(Joysticks.PANEL, COLLECT).whileHeld(
            new ParallelCommandGroup(
//                new SetCollector(ControlMode.Throttle, Constants.COLLECT_THROTTLE),
                new SetIndexer(ControlMode.Throttle, Constants.COLLECT_THROTTLE),
                new SetAccelerator(ControlMode.Throttle, -Constants.COLLECT_THROTTLE)
            )
        );

        getButton(Joysticks.PANEL, EXPEL).whileHeld(
            new ParallelCommandGroup(
//                new SetCollector(ControlMode.Throttle, -Constants.EXPEL_THROTTLE),
                new SetIndexer(ControlMode.Throttle, -Constants.EXPEL_THROTTLE),
                new SetAccelerator(ControlMode.Throttle, -Constants.EXPEL_THROTTLE)
            )
        );

        getButton(Joysticks.PANEL, INDEXER_INTAKE).whileHeld(
            new ParallelCommandGroup(
                new SetIndexer(ControlMode.Throttle, Constants.INDEXER_THROTTLE)
//                new SetCollector(ControlMode.Throttle, Constants.INDEXER_THROTTLE)
            )
        );

        getButton(Joysticks.PANEL, INDEXER_EXPEL).whileHeld(
            new ParallelCommandGroup(
                new SetIndexer(ControlMode.Throttle, -Constants.INDEXER_THROTTLE)
//                new SetCollector(ControlMode.Throttle, -Constants.INDEXER_THROTTLE)
            )
        );

        getButton(Joysticks.PANEL, SHOOT_1).whileHeld(
            new ParallelCommandGroup(
                new SetAccelerator(ControlMode.Throttle, Constants.ACCELERATOR_THROTTLE_1),
                new SetShooter(ControlMode.Throttle, Constants.FLYWHEEL_THROTTLE_1)
            )
        );

        getButton(Joysticks.PANEL, SHOOT_2).whileHeld(
            new ParallelCommandGroup(
                new SetAccelerator(ControlMode.Throttle, Constants.ACCELERATOR_THROTTLE_2),
                new SetShooter(ControlMode.Throttle, Constants.FLYWHEEL_THROTTLE_2)
            )
        );

        getButton(Joysticks.PANEL, SHOOT_3).whileHeld(
            new ParallelCommandGroup(
                new SetAccelerator(ControlMode.Throttle, Constants.ACCELERATOR_THROTTLE_3),
                new SetShooter(ControlMode.Throttle, Constants.FLYWHEEL_THROTTLE_3)
            )
        );
    }
}
