package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team1983.util.motors.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.Spark;


public class Collector extends SubsystemBase
{
    private DoubleSolenoid piston; // Piston controls whether it is out or stowed
    private Motor collectorMotor; // Collector has one motor, just the collectorMotor

   // Used in collector commands so that we have a constant for motor directions
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

    // For now we're just using the motorsForward constant because it's a good chance
    // We'll only have to collect at one speed
    public void setRollerThrottle(double throttle)
    {
        collectorMotor.set(ControlMode.Throttle, throttle);
    }

    // This simply tells us what positions are allowed
    public enum position
    {
        extended,
        retracted
    }

    // Sets collector position
    public void setCollectorPosition(boolean down)
    {
        piston.set(down ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public boolean isCollectorDown()
    {
        return piston.get() == DoubleSolenoid.Value.kForward;
    }

    public void setCollectorMotor(double speed)
    {
        collectorMotor.set(ControlMode.Throttle, speed);
    }
}

