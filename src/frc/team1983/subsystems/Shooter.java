package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Shooter extends SubsystemBase
{
    private MotorGroup accelerator;
    private MotorGroup flywheel;

    public Shooter()
    {
        accelerator = new MotorGroup(
            new Spark(RobotMap.Shooter.ACCELERATOR_1, RobotMap.Shooter.ACCELERATOR_1_REVERSED),
            new Spark(RobotMap.Shooter.ACCELERATOR_2, RobotMap.Shooter.ACCELERATOR_2_REVERSED)
        );

        flywheel = new MotorGroup(
            new Spark(RobotMap.Shooter.FLYWHEEL_1, RobotMap.Shooter.FLYWHEEL_1_REVERSED),
            new Spark(RobotMap.Shooter.FLYWHEEL_2, RobotMap.Shooter.FLYWHEEL_2_REVERSED)
        );
    }

    /**
     * @param controlMode The control mode the accelerator should run in
     * @param value The setpoint at which the accelerator should run
     */
    public void setAccelerator(ControlMode controlMode, double value)
    {
        accelerator.set(controlMode, value);
    }

    /**
     * @param controlMode The control mode the flywheel should run in
     * @param value The setpoint at which the flywheel should run
     */
    public void setFlywheel(ControlMode controlMode, double value)
    {
        flywheel.set(controlMode, value);
    }

    /**
     * @param controlMode The control mode the motor should run in
     * @param acceleratorValue The setpoint at which the accelerator should run
     * @param flywheelValue The setpoint at which the flywheel should run
     */
    public void set(ControlMode controlMode, double acceleratorValue, double flywheelValue)
    {
        setAccelerator(controlMode, acceleratorValue);
        setFlywheel(controlMode, flywheelValue);
    }
}
