package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;

public class Climber extends SubsystemBase
{
    private DoubleSolenoid piston; //piston controls whether it is out or stowed

    private position currentPosition;

    public Climber()
    {
        piston = new DoubleSolenoid(RobotMap.COMPRESSOR, RobotMap.Climber.PISTON_FORWARD,
                RobotMap.Climber.PISTON_REVERSE);
    }

    public enum position
    {
        extended,
        retracted
    }

    public void setClimberPosition(position p)
    {
        if (p == position.extended)
            piston.set(DoubleSolenoid.Value.kForward);
        else
            piston.set(DoubleSolenoid.Value.kReverse);


        currentPosition = p;
    }

    public position getClimberPosition()
    {
        return currentPosition;
    }
}
