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

public class Drivebase extends SubsystemBase
{
	public static final double FEET_PER_TICK = (6.0 * Math.PI / 12.0) / (8.69);
	public static final double METERS_PER_TICK = Units.feetToMeters(FEET_PER_TICK);
    public static final double kS = 0.0, kV = 0.0, kA = 0.0; // TODO: calculate
    public static final double kP = 0.0, kI = 0.0, kD = 0.0; // TODO: calculate

    private Motor leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3;

    private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.feetToMeters(Constants.TRACK_WIDTH));
    private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Robot.getInstance().getNavX().getHeading());

    private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV, kA);

    private PIDController leftPIDController = new PIDController(kP, kI, kD);
    private PIDController rightPIDController = new PIDController(kP, kI, kD);

    private Pose2d pose;

    public Drivebase()
    {
        leftMotor1 = new Motor(RobotMap.Drivebase.LEFT_1, RobotMap.Drivebase.LEFT_1_REVERSED);
        leftMotor2 = new Motor(RobotMap.Drivebase.LEFT_2, RobotMap.Drivebase.LEFT_2_REVERSED);
        leftMotor3 = new Motor(RobotMap.Drivebase.LEFT_3, RobotMap.Drivebase.LEFT_3_REVERSED);

        leftMotor2.follow(leftMotor1);
        leftMotor3.follow(leftMotor2);

        rightMotor1 = new Motor(RobotMap.Drivebase.RIGHT_1, RobotMap.Drivebase.RIGHT_1_REVERSED);
        rightMotor2 = new Motor(RobotMap.Drivebase.RIGHT_2, RobotMap.Drivebase.RIGHT_2_REVERSED);
        rightMotor3 = new Motor(RobotMap.Drivebase.RIGHT_3, RobotMap.Drivebase.RIGHT_3_REVERSED);

        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);
    }

    public void periodic()
    {
        pose = odometry.update(
        	Robot.getInstance().getNavX().getHeading(),
	        getLeftMeters(),
	        getRightMeters()
        );
    }

    // Rotations
    public double getLeftPosition()
    {
        return leftMotor1.getEncoder().getPosition();
    }

    // Rotations
    public double getRightPosition()
    {
        return rightMotor1.getEncoder().getPosition();
    }

    // feet
    public double getLeftFeet()
    {
        return getLeftPosition() * FEET_PER_TICK;
    }

    // feet
    public double getRightFeet()
    {
        return getRightPosition() * FEET_PER_TICK;
    }

    // feet
    public double getLeftMeters()
    {
        return getLeftPosition() * METERS_PER_TICK;
    }

    // feet
    public double getRightMeters()
    {
        return getRightPosition() * METERS_PER_TICK;
    }

    // RPM
    public double getLeftVelocity()
    {
        return leftMotor1.getEncoder().getVelocity();
    }

    // RPM
    public double getRightVelocity()
    {
        return rightMotor1.getEncoder().getVelocity();
    }

    // f/s
    public double getLeftFeetPerSecond()
    {
        return getLeftVelocity() * FEET_PER_TICK;
    }

    // f/s
    public double getRightFeetPerSecond()
    {
        return getRightVelocity() * FEET_PER_TICK;
    }

    // m/s
    public double getLeftMetersPerSecond()
    {
        return getLeftVelocity() * METERS_PER_TICK;
    }

    // m/s
    public double getRightMetersPerSecond()
    {
        return getRightVelocity() * METERS_PER_TICK;
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

    // % Throttle
    public void setLeft(double value)
    {
        leftMotor1.set(value);
        leftMotor2.set(value);
        leftMotor3.set(value);
    }

    // % Throttle
    public void setRight(double value)
    {
        rightMotor1.set(value);
        rightMotor2.set(value);
        rightMotor3.set(value);
    }

    // % Throttle
    public void set(double left, double right)
    {
        setLeft(left);
        setRight(right);
    }

    // Volts
    public void setLeftVolts(double volts)
    {
        setLeft(volts / 12.0);
    }

    // Volts
    public void setRightVolts(double volts)
    {
        setRight(volts / 12.0);
    }

    // Volts
    public void setVolts(double leftVolts, double rightVolts)
    {
        setLeftVolts(leftVolts);
        setRightVolts(rightVolts);
    }
}
