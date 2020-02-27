package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;
import frc.team1983.util.motors.ControlMode;

public class SetArticulation extends CommandBase
{
    private Shooter shooter;
    private ControlMode controlMode;
    private double value;

    public SetArticulation(Shooter shooter, double value)
    {
        this.shooter = shooter;
        this.value = value;
    }

    public SetArticulation(double value)
    {
        this(Robot.getInstance().getShooter(), value);
    }

    @Override
    public void execute()
    {
        if (shooter.getArticulationPosition() <= shooter.UPPER_LIMIT && shooter.getArticulationPosition() > shooter.LOWER_LIMIT)
            shooter.setArticulation(value);
        else if (shooter.getArticulationPosition() >= shooter.UPPER_LIMIT && value < 0.0)
            shooter.setArticulation(value);
        else if (shooter.getArticulationPosition() <= shooter.LOWER_LIMIT && value > 0)
            shooter.setArticulation(value);
        else shooter.setArticulation(0);
    }

    @Override
    public void end(boolean interrupted)
    {
        shooter.setArticulation(0.0);
    }
}
