package frc.team1983;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    private Joystick leftJoystick, rightJoystick;

    public OI()
    {
        leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
        rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
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
        if(Math.abs(raw) < Constants.JOYSTICK_DEADZONE)
            return 0;

        return raw > 0 ? raw * raw : -(raw * raw);
    }
}
