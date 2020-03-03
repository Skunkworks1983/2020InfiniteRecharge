package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;
import frc.team1983.util.HSVColor;
import frc.team1983.util.sensors.ColorSensor;


public class ControlPanel extends SubsystemBase
{
    public static final double ROTATIONS_PER_TICK = 42;
    
    public boolean desiredFoldedState = false;
    private MotorGroup roller;
    private DoubleSolenoid extender;
    private ColorSensor colorSensor;
    private boolean alreadyPolled;
    private HSVColor.Color assignedColor;
    
    public ControlPanel()
    {
        roller = new MotorGroup(
            new Spark(RobotMap.ControlPanel.ROLLER, RobotMap.ControlPanel.ROLLER_REVERSED)
        );

        extender = new DoubleSolenoid(RobotMap.ControlPanel.PISTON_FORWARD, RobotMap.ControlPanel.PISTON_REVERSE);
        
        colorSensor= new ColorSensor();
    }
    
    public void setRoller(ControlMode controlMode, double value)
    {
        roller.set(controlMode, value);
    }
    
    public void zero()
    {
        roller.zero();
    }
    
    public void setExtended(boolean shouldExtend)
    {
        extender.set(shouldExtend ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }

    public boolean isFolded()
    {
        return extender.get() == DoubleSolenoid.Value.kReverse;
    }

    public double getRotations()
    {
        return roller.getPosition() * ROTATIONS_PER_TICK;
    }

    public double getVelocity()
    {
        return roller.getVelocity();
    }
    
    public HSVColor getHSVColor()
    {
        return colorSensor.getHSVColor();
    }
    
    public HSVColor.Color getColor() { return colorSensor.getHSVColor().getColor(); }
    
    public void setAlreadyPolled(boolean alreadyPolled)
    {
        this.alreadyPolled = alreadyPolled;
    }
    
    public boolean isAlreadyPolled()
    {
        return alreadyPolled;
    }
    
    public void setAssignedColor(HSVColor.Color assignedColor)
    {
        this.assignedColor = assignedColor;
    }
    
    public HSVColor.Color getAssignedColor()
    {
        return assignedColor;
    }
}
