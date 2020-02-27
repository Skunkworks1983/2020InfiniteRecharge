package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetArticulationPosition extends PIDCommand
{
    private Shooter shooter;

    public SetArticulationPosition(Shooter shooter, double setpoint)
    {
        super(
            new PIDController(Shooter.kP, Shooter.kI, Shooter.kD),
            () -> shooter.getArticulationPosition(),
            () -> shooter.getArticulationPosition() - setpoint,
            output -> shooter.setArticulation( output),
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
        shooter.setArticulation(0);
    }
}
