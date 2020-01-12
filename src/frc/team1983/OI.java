package frc.team1983;

import edu.wpi.first.wpilibj.Joystick;

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

    public static final double JOYSTICK_DEADZONE = 0.15;

    private Joystick leftJoystick, rightJoystick;

    public OI()
    {
        leftJoystick = new Joystick(Joysticks.LEFT.getPort());
        rightJoystick = new Joystick(Joysticks.RIGHT.getPort());
    }

    public double getLeft()
    {
        return -scale(leftJoystick.getY());
    }

    public double getRight()
    {
        return -scale(rightJoystick.getY());
    }

    private double scale(double raw)
    {
        if(Math.abs(raw) < JOYSTICK_DEADZONE)
            return 0;

        return raw > 0 ? raw * raw : -(raw * raw);
    }
}
