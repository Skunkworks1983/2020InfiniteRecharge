package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;


public class Drivebase extends SubsystemBase
{
    private Motor leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3;

    public Drivebase()
    {
        leftMotor1 = new Motor(RobotMap.Motor.LEFT1.getPort(), RobotMap.Motor.LEFT1.isReversed());
        leftMotor2 = new Motor(RobotMap.Motor.LEFT2.getPort(), RobotMap.Motor.LEFT2.isReversed());
        leftMotor3 = new Motor(RobotMap.Motor.LEFT3.getPort(), RobotMap.Motor.LEFT3.isReversed());

        rightMotor1 = new Motor(RobotMap.Motor.RIGHT1.getPort(), RobotMap.Motor.RIGHT1.isReversed());
        rightMotor2 = new Motor(RobotMap.Motor.RIGHT2.getPort(), RobotMap.Motor.RIGHT2.isReversed());
        rightMotor3 = new Motor(RobotMap.Motor.RIGHT3.getPort(), RobotMap.Motor.RIGHT3.isReversed());

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
