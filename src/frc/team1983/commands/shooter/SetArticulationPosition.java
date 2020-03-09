package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.Shooter;

public class SetArticulationPosition extends CommandBase
{
    private Shooter shooter;
    private double setPoint;

    public SetArticulationPosition(Shooter shooter, double setPoint)
    {
        this.shooter = shooter;
        this.setPoint = setPoint;

        addRequirements(shooter);
    }

    public SetArticulationPosition(double setPoint)
    {
        this(Robot.getInstance().getShooter(), setPoint);
    }

    @Override
    public void initialize()
    {
        shooter.setBrake(true);
    }

    @Override
    public void execute()
    {
        double currentAngle = shooter.getArticulationPosition();
        double error = -(currentAngle - setPoint);
        double throttle = Shooter.KP * error + Shooter.KF;

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
