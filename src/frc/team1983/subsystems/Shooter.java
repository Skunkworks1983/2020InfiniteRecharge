package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;
import frc.team1983.util.sensors.DutyCycleEncoder;

public class Shooter extends SubsystemBase
{

    public static final double KP = 58, KF = 0.01;
    public double voltageRamp = 2.5;

    //Actual limits should ot be used for anything else than calculating setpoints
    //Actual limits are used to create safety limits and all other setpoints
    //Tuned on 03/08/2020
    private static final double UPPER_LIMIT = 0.636;
    private static final double LOWER_LIMIT = 0.215;

    public static final double UPPER_SAFETY_LIMIT = UPPER_LIMIT - 0.01;
    public static final double LOWER_SAFETY_LIMIT = LOWER_LIMIT + 0.01;

    //Tuned on 03/08/2020 at 90 percent throttle
    public static final double TRENCH = UPPER_LIMIT - 0.187;
    //Tuned on 03/08/2020 at 90 percent throttle
    public static final double IN_FRONT_OF_PORT_CLOSE_PILLAR = UPPER_LIMIT - 0.264;
    //Tuned on 03/08/2020 at 90 percent throttle
    public static final double CROSS_FIELD = UPPER_LIMIT - 0.21;

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
        SmartDashboard.putNumber("Accelerator Value", acceleratorValue);
        SmartDashboard.putNumber("Flywheel Value", flywheelValue);
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
