package frc.team1983.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team1983.Robot;
import frc.team1983.constants.Constants;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Limelight;

public class TargetAlignment extends PIDCommand
{
    private static final double kP = 0.011, kI = 0.05, kD = 0.0005, kF = 0.04;
    private static final double MAX_OUTPUT = 0.5;
    private static final double INTEGRATOR_RANGE = 0.4;
    private static final double INTEGRATOR_ENABLED_THRESHOLD = 7.0; // degrees
    private Drivebase drivebase;
    private Limelight limelight;

    private double targetAngle;
    private double setpoint;
    private double previousError;

    public TargetAlignment(Drivebase drivebase, Limelight limelight, double targetAngle)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> drivebase.getHeading().getDegrees(),
            () -> targetAngle,
            output -> {
                double feedforward = kF * Math.signum(output);
                drivebase.set(ControlMode.Throttle,
                    MathUtil.clamp(-output - feedforward, -MAX_OUTPUT, MAX_OUTPUT),
                    MathUtil.clamp(output + feedforward, -MAX_OUTPUT, MAX_OUTPUT)
                );
            },
            drivebase
        );

        this.drivebase = drivebase;
        this.limelight = limelight;

        this.targetAngle = targetAngle;
        this.setpoint = targetAngle;

        getController().enableContinuousInput(-180, 180);
        getController().setIntegratorRange(-INTEGRATOR_RANGE, INTEGRATOR_RANGE);

        addRequirements(drivebase);
    }

    public TargetAlignment(Drivebase drivebase, Limelight limelight, Pose2d pose)
    {
    	this(drivebase, limelight, getHeadingToTargetFromPose(pose));
    }

    public TargetAlignment(Pose2d pose)
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), pose);
    }

    public TargetAlignment(double targetAngle)
    {
        this(Robot.getInstance().getDrivebase(), Robot.getInstance().getLimelight(), targetAngle);
    }

    @Override
    public void initialize()
    {
        limelight.setLedMode(Limelight.LedMode.FORCE_ON);
        m_setpoint = () -> setpoint;
        super.initialize();
    }

    @Override
    public void execute()
    {
        if(limelight.isTargetDetected())
        {
            setpoint = wrapAngle(drivebase.getHeading().getDegrees() - limelight.getX());
        }
        else
        {
            setpoint = targetAngle;
        }

        if(Math.signum(previousError) != Math.signum(getController().getPositionError()))
        {
            getController().reset();
        }

        if(Math.abs(getController().getPositionError()) > INTEGRATOR_ENABLED_THRESHOLD)
        {
            getController().setI(0);
        }
        else
        {
            getController().setI(kI);
        }

        super.execute();
        previousError = getController().getPositionError();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        drivebase.set(ControlMode.Throttle, 0.0, 0.0);
        limelight.setLedMode(Limelight.DEFAULT_LED_MODE);
    }

    private static double getHeadingToTargetFromPose(Pose2d pose)
    {
    	double x = pose.getTranslation().getX() - Constants.Pose.PORT_TARGET.getTranslation().getX();
        double y = pose.getTranslation().getY() - Constants.Pose.PORT_TARGET.getTranslation().getY();

        return wrapAngle(Math.atan(y / x));
    }

    /**
     * @param angle (-Infinity, Infinity)
     * @return angle [-180, 180]
     */
    protected static double wrapAngle(double angle)
    {
    	angle = angle % 360;

    	if(angle > 180)
        {
        	return angle - 360;
        }
    	else if(angle < -180)
        {
            return angle + 360;
        }
    	else
        {
            return angle;
        }
    }
}