package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.util.Units;
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
            new Spark(RobotMap.Collector.MOTOR_1, RobotMap.Collector.MOTOR_1_REVERSED),
            new Spark(RobotMap.Collector.MOTOR_2, RobotMap.Collector.MOTOR_2_REVERSED),
            new Spark(RobotMap.Collector.MOTOR_3, RobotMap.Collector.MOTOR_3_REVERSED),
            new Spark(RobotMap.Collector.MOTOR_4, RobotMap.Collector.MOTOR_4_REVERSED)
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
