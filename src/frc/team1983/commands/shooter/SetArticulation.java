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

    public SetArticulation(Shooter shooter, ControlMode controlMode, double value)
    {
        this.shooter = shooter;
        this.controlMode = controlMode;
        this.value = value;
    }

    public SetArticulation(ControlMode controlMode, double value)
    {
        this(Robot.getInstance().getShooter(), controlMode, value);
    }

    @Override
    public void execute()
    {
        shooter.setArticulation(controlMode, value);

        //System.out.println(shooter.articulationEncoder.get());
    }

    @Override
    public void end(boolean interrupted)
    {
        shooter.setArticulation(ControlMode.Throttle, 0.0);

        //System.out.println(shooter.articulationEncoder.get());
    }
}
