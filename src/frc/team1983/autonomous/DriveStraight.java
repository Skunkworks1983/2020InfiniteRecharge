package frc.team1983.autonomous;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;

public class DriveStraight extends PIDCommand
{
	private static final double kP_DISTANCE = 0.4, kI_DISTANCE = 0.0, kD_DISTANCE = 0.0, kF_DISTANCE = 0.01;
	private static final double kP_HEADING = 0.015, kF_HEADING = 0.01;
	private Drivebase drivebase;

	private double throttle;
	private double targetHeading = 0;

	/**
 	 * @param drivebase the drivebase
	 * @param throttle throttle to drive straight at, + throttle goes forward, - throttle goes backward
	 * @param meters the distance to travel, always positive
	 */
	public DriveStraight(Drivebase drivebase, double throttle, double meters)
	{
		super(
			new PIDController(kP_DISTANCE, kI_DISTANCE, kD_DISTANCE),
			() -> drivebase.getLeftMeters() + drivebase.getRightMeters() / 2.0,
			Math.signum(throttle) * Math.abs(meters) + drivebase.getLeftMeters() + drivebase.getRightMeters() / 2.0,
			output -> {
				double clampedThrottle = throttle >= 0 ? Math.min(output, throttle) : Math.max(output, throttle);
				drivebase.set(ControlMode.Throttle, clampedThrottle, clampedThrottle);
			},
			drivebase
		);
		this.drivebase = drivebase;
		this.throttle = throttle;

		addRequirements(drivebase);
	}

	/**
	 * @param throttle throttle to drive straight at, + throttle goes forward, - throttle goes backward
	 * @param meters the distance to travel, always positive
	 */
	public DriveStraight(double throttle, double meters)
	{
		this(Robot.getInstance().getDrivebase(), throttle, meters);
	}

	@Override
	public void initialize()
	{
		targetHeading = drivebase.getHeading().getDegrees();
		m_useOutput = output -> {
			double clampedThrottle = throttle >= 0 ? Math.min(output, throttle) : Math.max(output, throttle);
			double turnThrottle = kP_HEADING * (drivebase.getHeading().getDegrees() - targetHeading) + kF_HEADING * Math.signum(output);
			drivebase.set(ControlMode.Throttle, clampedThrottle + turnThrottle + kF_DISTANCE, clampedThrottle - turnThrottle + kF_DISTANCE);
			System.out.println(getController().getPositionError());
		};
		super.initialize();
	}

	@Override
	public void end(boolean interrupted)
	{
		super.end(interrupted);
		drivebase.set(ControlMode.Throttle, 0.0, 0.0);
	}
}
