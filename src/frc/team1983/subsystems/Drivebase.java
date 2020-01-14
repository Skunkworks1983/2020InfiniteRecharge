package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
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
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.MotorGroup;

public class Drivebase extends SubsystemBase
{
	public static final double FEET_PER_TICK = (6.0 * Math.PI / 12.0) / (8.69);
	public static final double METERS_PER_TICK = Units.feetToMeters(FEET_PER_TICK);
    public static final double kS = 0.0, kV = 0.0, kA = 0.0; // TODO: calculate
    public static final double kP = 0.0, kI = 0.0, kD = 0.0; // TODO: calculate

    private MotorGroup left, right;

    private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.feetToMeters(Constants.TRACK_WIDTH));
    private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Robot.getInstance().getNavX().getHeading());

    private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV, kA);

    private PIDController leftPIDController = new PIDController(kP, kI, kD);
    private PIDController rightPIDController = new PIDController(kP, kI, kD);

    private Pose2d pose;

    public Drivebase()
    {
        left = new MotorGroup(
            new Motor(RobotMap.Drivebase.LEFT_1, RobotMap.Drivebase.LEFT_1_REVERSED),
            new Motor(RobotMap.Drivebase.LEFT_2, RobotMap.Drivebase.LEFT_2_REVERSED),
            new Motor(RobotMap.Drivebase.LEFT_3, RobotMap.Drivebase.LEFT_3_REVERSED)
        );

        right = new MotorGroup(
            new Motor(RobotMap.Drivebase.RIGHT_1, RobotMap.Drivebase.RIGHT_1_REVERSED),
            new Motor(RobotMap.Drivebase.RIGHT_2, RobotMap.Drivebase.RIGHT_2_REVERSED),
            new Motor(RobotMap.Drivebase.RIGHT_3, RobotMap.Drivebase.RIGHT_3_REVERSED)
        );
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
     * @param throttle Sets the percent output of the left motors
     */
    public void setLeft(double throttle)
    {
        left.set(throttle);
    }

    /**
     * @param throttle Sets the percent output of the right motors
     */
    public void setRight(double throttle)
    {
        right.set(throttle);
    }

    /**
     * @param leftThrottle Sets the percent output of the left motors
     * @param rightThrottle Sets the percent output of the right motors
     */
    public void set(double leftThrottle, double rightThrottle)
    {
        setLeft(leftThrottle);
        setRight(rightThrottle);
    }

    /**
     * @param volts Sets the voltage output of the left motors
     */
    public void setLeftVolts(double volts)
    {
        setLeft(volts / 12.0);
    }

    /**
     * @param volts Sets the voltage output of the right motors
     */
    public void setRightVolts(double volts)
    {
        setRight(volts / 12.0);
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

    public PIDController getLeftPIDController()
    {
        return leftPIDController;
    }

    public PIDController getRightPIDController()
    {
        return rightPIDController;
    }

    public Pose2d getPose()
    {
        return pose;
    }
}
