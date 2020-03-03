package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.Robot;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;


public class ControlPanel extends SubsystemBase
{
    //TODO find targetDistance
    public static final double targetDistance = 254;
    public static final double rotationControlSpeed = 0.5;
    public static final double positionControlSpeed = 0.5;

    private MotorGroup roller;
    private DoubleSolenoid extender;

    public ControlPanel()
    {
        roller = new MotorGroup(
            new Spark(RobotMap.ControlPanel.ROLLER, RobotMap.ControlPanel.ROLLER_REVERSED)
        );

        extender = new DoubleSolenoid(RobotMap.ControlPanel.PISTON_FORWARD, RobotMap.ControlPanel.PISTON_REVERSE);
    }

    public void zero()
    {
        roller.zero();
    }

    public void setRoller(double value)
    {
        roller.set(ControlMode.Throttle, value);
    }

    public void setExtended(boolean shouldExtend)
    {
        extender.set(shouldExtend ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }

    public boolean getFolded()
    {
        return extender.get() == DoubleSolenoid.Value.kReverse;
    }

    public double getPosition()
    {
        return roller.getPosition();
    }

    public double getVelocity()
    {
        return roller.getVelocity();
    }


}
