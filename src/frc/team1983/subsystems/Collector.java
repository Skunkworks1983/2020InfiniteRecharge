package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Collector extends SubsystemBase
{
    private MotorGroup motorGroup;

    public Collector()
    {
        motorGroup = new MotorGroup(
            new Spark(RobotMap.Collector.MOTOR, RobotMap.Collector.MOTOR_REVERSED)
        );
    }

    /**
     * @param controlMode The control mode the collector should run in
     * @param value The setpoint at which the collector should run
     */
    public void set(ControlMode controlMode, double value)
    {
        motorGroup.set(controlMode, value);
    }
}
