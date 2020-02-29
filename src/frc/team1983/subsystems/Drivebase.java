package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.Robot;
import frc.team1983.constants.Constants;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.control.SparkPIDController;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Drivebase extends SubsystemBase
{
    // 2019
//	public static final double FEET_PER_TICK = (5.75 * Math.PI / 12.0) / (8.69);
    // 2020
    public static final double FEET_PER_TICK = (6.0 * Math.PI / 12.0) / (496.0 / 55.0);
	public static final double METERS_PER_TICK = Units.feetToMeters(FEET_PER_TICK);

	// 2019
//    public static final double kS = 0.120, kV = 2.08, kA = 0.0;
//    public static final double kP = 1.0e-4, kI = 0.0, kD = 0.0;
    // 2020
    public static final double kS = 0.015, kV = 2.38, kA = 0.0;
    public static final double kP = 1.0e-4, kI = 0.0, kD = 0.0;

    private MotorGroup left, right;

    private DifferentialDriveKinematics kinematics;
    private DifferentialDriveOdometry odometry;

    private SimpleMotorFeedforward feedforward;

    private SparkPIDController leftPIDController;
    private SparkPIDController rightPIDController;

    private Pose2d pose;

    public Drivebase()
    {
        left = new MotorGroup(
            new Spark(RobotMap.Drivebase.LEFT_1, RobotMap.Drivebase.LEFT_1_REVERSED),
            new Spark(RobotMap.Drivebase.LEFT_2, RobotMap.Drivebase.LEFT_2_REVERSED)
        );

        right = new MotorGroup(
            new Spark(RobotMap.Drivebase.RIGHT_1, RobotMap.Drivebase.RIGHT_1_REVERSED),
            new Spark(RobotMap.Drivebase.RIGHT_2, RobotMap.Drivebase.RIGHT_2_REVERSED)
        );

        kinematics = new DifferentialDriveKinematics(Units.feetToMeters(Constants.TRACK_WIDTH));
        odometry = new DifferentialDriveOdometry(Robot.getInstance().getNavX().getHeading());

        feedforward = new SimpleMotorFeedforward(kS, kV, kA);

        leftPIDController = new SparkPIDController((Spark) left.getMaster());
        leftPIDController.setGains(kP, kI, kD);

        rightPIDController = new SparkPIDController((Spark) right.getMaster());
        rightPIDController.setGains(kP, kI, kD);
    }

    public void periodic()
    {
        pose = odometry.update(
            Robot.getInstance().getNavX().getHeading(),
            getLeftMeters(),
            getRightMeters()
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
        return right.getVelocity();
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

    public DifferentialDriveWheelSpeeds getSpeeds()
    {
        return new DifferentialDriveWheelSpeeds(
            getLeftMetersPerSecond(),
            getRightMetersPerSecond()
        );
    }

    public SimpleMotorFeedforward getFeedforward()
    {
        return feedforward;
    }

    public DifferentialDriveKinematics getKinematics()
    {
        return kinematics;
    }

    public SparkPIDController getLeftPIDController()
    {
        return leftPIDController;
    }

    public SparkPIDController getRightPIDController()
    {
        return rightPIDController;
    }

    public Pose2d getPose()
    {
        return pose;
    }

    /**
     * @param pose Pose to set the drivebase to
     */
    public void setPose(Pose2d pose)
    {
        zero();
        this.pose = pose;

        odometry.resetPosition(pose, pose.getRotation());
        Robot.getInstance().getNavX().setHeading(pose.getRotation().getDegrees());
    }

    /**
     * @param brake If the drivebase should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        left.setBrake(brake);
        right.setBrake(brake);
    }
}
