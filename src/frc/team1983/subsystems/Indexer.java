package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Indexer extends SubsystemBase
{
    private MotorGroup motorGroup;

    public Indexer()
    {
        motorGroup = new MotorGroup(
            new Spark(RobotMap.Indexer.MOTOR_1, RobotMap.Indexer.MOTOR_1_REVERSED),
            new Spark(RobotMap.Indexer.MOTOR_2, RobotMap.Indexer.MOTOR_2_REVERSED)
        );
    }

    /**
     * @param controlMode The control mode the indexer should run in
     * @param value The setpoint at which the indexer should run
     */
    public void set(ControlMode controlMode, double value)
    {
        motorGroup.set(controlMode, value);
    }
}
