package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.Constants;

public class Drivebase extends SubsystemBase
{
    private Motor leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3;

    public Drivebase()
    {
        leftMotor1 = new Motor(Constants.Motor.LEFT1.getPort(), Constants.Motor.LEFT1.isReversed());
        leftMotor2 = new Motor(Constants.Motor.LEFT2.getPort(), Constants.Motor.LEFT2.isReversed());
        leftMotor3 = new Motor(Constants.Motor.LEFT3.getPort(), Constants.Motor.LEFT3.isReversed());

        rightMotor1 = new Motor(Constants.Motor.RIGHT1.getPort(), Constants.Motor.RIGHT1.isReversed());
        rightMotor2 = new Motor(Constants.Motor.RIGHT2.getPort(), Constants.Motor.RIGHT2.isReversed());
        rightMotor3 = new Motor(Constants.Motor.RIGHT3.getPort(), Constants.Motor.RIGHT3.isReversed());

    }

    public void setLeft(double value)
    {
        leftMotor1.set(value);
        leftMotor2.set(value);
        leftMotor3.set(value);
    }

    public void setRight(double value)
    {
        rightMotor1.set(value);
        rightMotor2.set(value);
        rightMotor3.set(value);
    }

    public void set(double left, double right)
    {
        setLeft(left);
        setRight(right);
    }
}
