package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team1983.util.motors.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.Spark;


public class Collector extends SubsystemBase
{
    private DoubleSolenoid piston; //piston controls whether it is out or stowed
    private Motor collectorMotor; //collector has one motor, just the collectorMotor

   // used in collector commands so that we have a constant for motor directions
    public static double motorsForward = 1;
    public static double motorsReversed = -1;
    public static double motorsOff = 0;

    private position currentPosition = position.retracted;


    public Collector()
    {
        collectorMotor = new Spark(RobotMap.Collector.COLLECTOR_MOTOR, RobotMap.Collector.COLLECTOR_MOTOR_REVERSED);

        piston = new DoubleSolenoid(RobotMap.COMPRESSOR, RobotMap.Collector.PISTON_FORWARD,
                RobotMap.Collector.PISTON_REVERSE);
    }

    //TODO: uhhh is this going to be throttale-able
    //for now we're just using the motorsForward constant because it's a good chance
    // we'll only have to collect at one speed
    public void setRollerThrottle(double throttle)
    {
        collectorMotor.set(ControlMode.Throttle, throttle);
    }

    //TODO: let's assume that there's only two positions? if not- CHANGE
    //this simply tells us what positions are allowed
    public enum position
    {
        extended,
        retracted
    }

    //sets collector position
    public void setCollectorPosition(position p)
    {
        if (p == position.retracted)
        {
            piston.set(DoubleSolenoid.Value.kReverse);
        }
        else
        {
            piston.set(DoubleSolenoid.Value.kForward);
        }

        currentPosition = p;
    }

    //gets collector position, there's really only two options right now
    public position getCollectorPosition()
    {
        return currentPosition;
    }

    public boolean getCollectorStatus()
    {
        return piston.get() == DoubleSolenoid.Value.kReverse;
    }


    public void setCollectorMotor(double speed)
    {
        collectorMotor.set(ControlMode.Throttle, speed);
    }


}

