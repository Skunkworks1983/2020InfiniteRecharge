package frc.team1983.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Shooter;

public class SetArticulationPosition extends CommandBase
{
    private Shooter shooter;
    private OI oi;
    private double setPoint;

    private static double KP = 65, KF = 0;

    public SetArticulationPosition(Shooter shooter, OI oi, double setPoint)
    {
        this.shooter = shooter;
        this.setPoint = setPoint;
        this.oi = oi;

        addRequirements(shooter);
    }

    public SetArticulationPosition(double setPoint)
    {
        this(Robot.getInstance().getShooter(), Robot.getInstance().getOI(), setPoint);
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
//        //Tuned on 02/27/2020
//        double throttle = 65.0 * -(currentAngle - setPoint);
        double error = -(currentAngle - setPoint);
        double throttle = KP * error + KF;

        if (Math.abs(oi.getOperatorY()) > 0.05)
        {
            throttle = oi.getOperatorY();
            setPoint = shooter.getArticulationPosition();
        }

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
