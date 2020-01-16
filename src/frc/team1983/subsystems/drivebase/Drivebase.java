package frc.team1983.subsystems.drivebase;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;


public class Drivebase extends SubsystemBase
{
    private MotorGroup left, right;

    public Drivebase()
    {
        left = new MotorGroup("Left Drivebase",
                new Spark(RobotMap.Drivebase.Motor.LEFT1.getPort(), RobotMap.Drivebase.Motor.LEFT1.isReversed()),
                new Spark(RobotMap.Drivebase.Motor.LEFT2.getPort(), RobotMap.Drivebase.Motor.LEFT2.isReversed()),
                new Spark(RobotMap.Drivebase.Motor.LEFT3.getPort(), RobotMap.Drivebase.Motor.LEFT3.isReversed())
        );
        right = new MotorGroup("Right Drivebase",
                new Spark(RobotMap.Drivebase.Motor.RIGHT1.getPort(), RobotMap.Drivebase.Motor.RIGHT1.isReversed()),
                new Spark(RobotMap.Drivebase.Motor.RIGHT2.getPort(), RobotMap.Drivebase.Motor.RIGHT2.isReversed()),
                new Spark(RobotMap.Drivebase.Motor.RIGHT3.getPort(), RobotMap.Drivebase.Motor.RIGHT3.isReversed())
        );
    }

    public void zero()
    {
        left.zero();
        right.zero();
    }

    public void setLeft(ControlMode mode, double value)
    {
        left.set(mode, value);
    }

    public void setRight(ControlMode mode, double value)
    {
        right.set(mode, value);
    }

    public void set(ControlMode mode, double leftValue, double rightValue)
    {
        setLeft(mode, leftValue);
        setRight(mode, rightValue);
    }

    public void set(ControlMode mode, double value)
    {
        setLeft(mode, value);
        setRight(mode, value);
    }

    public double getLeftPosition()
    {
        return left.getPosition();
    }

    public double getRightPosition()
    {
        return right.getPosition();
    }

    public void setBrake(boolean brake)
    {
        left.setBrake(brake);
        right.setBrake(brake);
    }
}
