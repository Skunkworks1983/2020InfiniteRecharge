package frc.team1983.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;
import frc.team1983.util.sensors.DutyCycleEncoder;

public class Shooter extends SubsystemBase
{

    public double voltageRamp = 2;

    //Limits based on encoder reading as of 02/28/2020
    public static final double UPPER_LIMIT = 0.9;
    public static final double LOWER_LIMIT = 0.54;

    //Tuned on 2/28/20 with manual loading and 90 percent throttle on accelerator and flywheel
    public static final double INNER_FRONT_PILLAR = 0.658;
    public static final double OUTER_BACK_PILLAR = 0.704;

    private MotorGroup accelerator;
    private MotorGroup flywheel;
    private MotorGroup articulation;

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

        articulation = new MotorGroup(
            new DutyCycleEncoder(1),
            new Spark(RobotMap.Shooter.ARTICULATION_1, RobotMap.Shooter.ARTICULATION_1_REVERSED)
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

    public void setBrake(boolean brake)
    {
        articulation.setBrake(brake);
    }

    public void setVoltageRamp(double volts)
    {
        accelerator.setVoltageRamp(volts);
        flywheel.setVoltageRamp(volts);
    }

    public void setArticulation(double speed)
    {
        articulation.set(ControlMode.Throttle, speed);
    }

    public double getArticulationPosition()
    {
        return articulation.getPositionTicks();
    }

    public double getAcceleratorVelocity()
    {
        return accelerator.getVelocity();
    }

    public double getFlywheelVelocity()
    {
        return flywheel.getVelocity();
    }
}
