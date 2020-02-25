package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;
import frc.team1983.util.sensors.NavX;

public class Drivebase extends SubsystemBase
{
	public static final double FEET_PER_TICK = (6.0 * Math.PI / 12.0) / (8.69);
	public static final double METERS_PER_TICK = Units.feetToMeters(FEET_PER_TICK);

	private NavX navX;
    private MotorGroup left, right;

    public Drivebase()
    {
    	navX = new NavX();

        left = new MotorGroup(
            new Spark(RobotMap.Drivebase.LEFT_1, RobotMap.Drivebase.LEFT_1_REVERSED),
            new Spark(RobotMap.Drivebase.LEFT_2, RobotMap.Drivebase.LEFT_2_REVERSED),
            new Spark(RobotMap.Drivebase.LEFT_3, RobotMap.Drivebase.LEFT_3_REVERSED)
        );

        right = new MotorGroup(
            new Spark(RobotMap.Drivebase.RIGHT_1, RobotMap.Drivebase.RIGHT_1_REVERSED),
            new Spark(RobotMap.Drivebase.RIGHT_2, RobotMap.Drivebase.RIGHT_2_REVERSED),
            new Spark(RobotMap.Drivebase.RIGHT_3, RobotMap.Drivebase.RIGHT_3_REVERSED)
        );
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        left.zero();
        right.zero();
    }

    /**
     * @return rotations
     */
    public double getLeftPosition()
    {
        return left.getPosition();
    }

    /**
     * @return rotations
     */
    public double getRightPosition()
    {
        return right.getPosition();
    }

    /**
     * @return feet
     */
    public double getLeftFeet()
    {
        return getLeftPosition() * FEET_PER_TICK;
    }

    /**
     * @return feet
     */
    public double getRightFeet()
    {
        return getRightPosition() * FEET_PER_TICK;
    }

    /**
     * @return meters
     */
    public double getLeftMeters()
    {
        return getLeftPosition() * METERS_PER_TICK;
    }

    /**
     * @return meters
     */
    public double getRightMeters()
    {
        return getRightPosition() * METERS_PER_TICK;
    }

    /**
     * @return rotations per minute (RPM)
     */
    public double getLeftVelocity()
    {
        return left.getVelocity();
    }

    /**
     * @return rotations per minute (RPM)
     */
    public double getRightVelocity()
    {
        return left.getVelocity();
    }

    /**
     * @return feet per second (f/s)
     */
    public double getLeftFeetPerSecond()
    {
        return getLeftVelocity() * FEET_PER_TICK / 60.0;
    }

    /**
     * @return feet per second (f/s)
     */
    public double getRightFeetPerSecond()
    {
        return getRightVelocity() * FEET_PER_TICK / 60.0;
    }

    /**
     * @return meters per second (m/s)
     */
    public double getLeftMetersPerSecond()
    {
        return getLeftVelocity() * METERS_PER_TICK / 60.0;
    }

    /**
     * @return meters per second (m/s)
     */
    public double getRightMetersPerSecond()
    {
        return getRightVelocity() * METERS_PER_TICK / 60.0;
    }

    /**
     * Set the motor output in a control mode
     *
     * @param controlMode The control mode the motor should run in
     * @param value The setpoint at which the motor should run
     */
    public void setLeft(ControlMode controlMode, double value)
    {
        left.set(controlMode, value);
    }

    /**
     * Set the motor output in a control mode
     *
     * @param controlMode The control mode the motor should run in
     * @param value The setpoint at which the motor should run
     */
    public void setRight(ControlMode controlMode, double value)
    {
        right.set(controlMode, value);
    }

    /**
     * @param leftThrottle Sets the percent output of the left motors
     * @param rightThrottle Sets the percent output of the right motors
     */
    public void set(ControlMode controlMode, double leftThrottle, double rightThrottle)
    {
        setLeft(controlMode, leftThrottle);
        setRight(controlMode, rightThrottle);
    }

    /**
     * @param volts Sets the voltage output of the left motors
     */
    public void setLeftVolts(double volts)
    {
        setLeft(ControlMode.Throttle, volts / 12.0);
    }

    /**
     * @param volts Sets the voltage output of the right motors
     */
    public void setRightVolts(double volts)
    {
        setRight(ControlMode.Throttle, volts / 12.0);
    }

    /**
     * @param leftVolts Sets the voltage output of the left motors
     * @param rightVolts Sets the voltage output of the right motors
     */
    public void setVolts(double leftVolts, double rightVolts)
    {
        setLeftVolts(leftVolts);
        setRightVolts(rightVolts);
    }

    public Rotation2d getHeading()
    {
    	return navX.getHeading();
    }

    public void setHeading(double heading)
    {
        navX.setHeading(heading);
    }

    public void resetHeading()
    {
        navX.reset();
    }
}
