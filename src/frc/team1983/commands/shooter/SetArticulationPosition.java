package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Limelight;
import frc.team1983.util.sensors.NavX;

public class SetArticulationPosition extends PIDCommand
{
    private static final double kP = 0.005, kI = 0.0, kD = 0.0;

    private Shooter shooter;
    private double setpoint;

    public SetArticulationPosition(Shooter shooter, double setpoint)
    {
        super(
            new PIDController(kP, kI, kD),
            () -> shooter.getArticulation(),
            () -> shooter.getArticulation() - setpoint,
            output -> shooter.setArticulation(ControlMode.Throttle, output),
            shooter
        );
        this.shooter = shooter;
    }

    public SetArticulationPosition(double setpoint)
    {
        this(Robot.getInstance().getShooter(), setpoint);
    }

    @Override
    public void initialize()
    {
        super.initialize();

    }

    @Override
    public void execute()
    {
        super.execute();
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);
        shooter.setArticulation(ControlMode.Throttle, 0);
    }
}
