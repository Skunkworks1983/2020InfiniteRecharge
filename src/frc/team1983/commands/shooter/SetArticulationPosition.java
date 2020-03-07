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
        //Tuned on 02/27/2020
        double throttle = 65.0 * -(currentAngle - setPoint);

        //Safety code that prevents shooter hood from continuing into hard stop
        if (shooter.getArticulationPosition() <= shooter.UPPER_LIMIT && shooter.getArticulationPosition() > shooter.LOWER_LIMIT)
            shooter.setArticulation(throttle);
        else if (shooter.getArticulationPosition() >= shooter.UPPER_LIMIT && throttle < 0)
            shooter.setArticulation(throttle);
        else if (shooter.getArticulationPosition() <= shooter.LOWER_LIMIT && throttle > 0)
            shooter.setArticulation(throttle);
        else shooter.setArticulation(0);

        System.out.println("Throttle: " + throttle);
    }
}
