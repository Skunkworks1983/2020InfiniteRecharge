package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Shooter;

public class SetArticulation extends CommandBase
{
    private Shooter shooter;
    private OI oi;

    private double targetAngle;

    public SetArticulation(Shooter shooter, OI oi)
    {
        this.shooter = shooter;
        this.oi = oi;

        addRequirements(shooter);
    }

    public SetArticulation()
    {
        this(Robot.getInstance().getShooter(), Robot.getInstance().getOI());
    }

    @Override
    public void initialize()
    {
        targetAngle = shooter.getArticulationPosition();
        shooter.setBrake(true);
    }

    @Override
    public void execute()
    {
        double currentAngle = shooter.getArticulationPosition();

        double error = -(currentAngle - targetAngle);
        double throttle = Shooter.KP * error + Shooter.KF;

        if (Math.abs(oi.getOperatorY()) > 0.05)
        {
            throttle = oi.getOperatorY();
            targetAngle = shooter.getArticulationPosition();
        }

        //Safety code that prevents shooter hood from continuing into hard stop
        if (shooter.getArticulationPosition() <= Shooter.UPPER_SAFETY_LIMIT && shooter.getArticulationPosition() > Shooter.LOWER_SAFETY_LIMIT)
            shooter.setArticulation(throttle);
        else if (shooter.getArticulationPosition() >= Shooter.UPPER_SAFETY_LIMIT && throttle < 0)
            shooter.setArticulation(throttle);
        else if (shooter.getArticulationPosition() <= Shooter.LOWER_SAFETY_LIMIT && throttle > 0)
            shooter.setArticulation(throttle);
        else shooter.setArticulation(0);
    }
}
