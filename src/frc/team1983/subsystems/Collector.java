package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;


public class Collector extends SubsystemBase
{

    public static class Setpoints
    {
        private static double STOW = 0;
        private static double COLLECT = 100;
        //TODO: figure out setpoints?
    }

    private MotorGroup roller;
    private DoubleSolenoid piston;
    public MotorGroup left, right;

    private position currentPosition = position.retracted;

    public static final double DEGREES_PER_TICK = 95.0; // TODO find value
    public static final double CLOSED_LOOP_TOLERANCE = 1.5; //TODO: find value
    public static final double STOW_ZONE = 6.0; //TODO change value

    public boolean automationEnabled = true; //TODO: look at automation
    public boolean desiredState = false; //TODO: do we want a folded state???

    public Collector()
    {
        Spark rollerMotor = new Spark(RobotMap.Collector.ROLLER, RobotMap.Collector.ROLLER_REVERSED);
        Spark rightMotor = new Spark(RobotMap.Collector.RIGHT, RobotMap. Collector.RIGHT_REVERSED);
        Spark leftMotor = new Spark(RobotMap.Collector.LEFT, RobotMap.Collector.LEFT_REVERSED);

        roller = new MotorGroup(rollerMotor, rollerMotor);

        piston = new DoubleSolenoid(RobotMap.COMPRESSOR, RobotMap.Collector.PISTON_FORWARD, RobotMap.Collector.PISTON_REVERSE);

        right = new MotorGroup(rightMotor, rightMotor);
        right.setConversionRatio(DEGREES_PER_TICK);

        left = new MotorGroup(leftMotor, leftMotor);
        left.setConversionRatio(DEGREES_PER_TICK);

       // right.setBrake(false);
        // left.setBrake(false);
    }

    //i dont fucking know
    public void setWristBrake(boolean brake)
    {
        left.setBrake(brake);
        right.setBrake(brake);
    }

    //TODO: uhhh is this going to be throttale-able
    public void setRollerThrottle(double throttle)
    {
        roller.set(ControlMode.Throttle, throttle);
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
}
