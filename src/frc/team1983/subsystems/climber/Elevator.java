package frc.team1983.subsystems.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.Robot;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Elevator extends SubsystemBase
{
    public static class Setpoints
    {

    }
//todo tune for 2020 robot
    public static final double INCHES_PER_TICK = (19.5 * 3.0) / 59.5;
    public static final double CLOSED_LOOP_TOLERANCE = 2.0;
    public static final double kG = 0.07;

    public boolean automationEnabled = true;

    public MotorGroup motorGroup;

    public Elevator()
    {
        motorGroup = new MotorGroup("Climbing Elevator",
                new Spark(RobotMap.Climber.Motor.Elevator1.getPort(), RobotMap.Climber.Motor.Elevator1.isReversed()),
                new Spark(RobotMap.Climber.Motor.Elevator2.getPort(), RobotMap.Climber.Motor.Elevator2.isReversed())
        );

        motorGroup.setConversionRatio(INCHES_PER_TICK);
//todo tune for 2020 robot
        motorGroup.setMovementAcceleration(90);
        motorGroup.setCruiseVelocity(90);
        motorGroup.setKP(0.05);

        motorGroup.setFFOperator(this);
        motorGroup.addFFTerm(Elevator -> kG);

        zero();
    }

    public void zero()
    {
        motorGroup.zero();
    }

    public void set(ControlMode mode, double value)
    {
        if(mode == ControlMode.Throttle) automationEnabled = false;
        motorGroup.set(mode, value);
    }

    public void setPosition(double position)
    {
        motorGroup.set(ControlMode.Position, position);
    }

    public double getPosition()
    {
        return motorGroup.getPositionTicks() * INCHES_PER_TICK;
    }

    public double getVelocity()
    {
        return motorGroup.getVelocity();
    }

    public void setBrake(boolean brake)
    {
        motorGroup.setBrake(brake);
    }

    public boolean isAtSetpoint()
    {
        return Math.abs(motorGroup.getPosition() - motorGroup.getSetpoint()) < CLOSED_LOOP_TOLERANCE;
    }
}
