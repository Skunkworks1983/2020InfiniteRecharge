package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.HSVColor;
import frc.team1983.util.sensors.ColorSensor;

public class RotationControl extends CommandBase
{
    private static final double NUM_ROTATIONS = 3.5;
    private static final double WEDGES_PER_ROTATION = 8;
    
    private ControlPanel controlPanel;
    private char previousColor;
    private char currentColor;
    private double totalWedges;
    
    public RotationControl(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }
    
    public RotationControl()
    {
        this(Robot.getInstance().getControlPanel());
    }
    
    @Override
    public void initialize()
    {
        previousColor = controlPanel.getColorMatch();
        System.out.println("initializing 'ROTATION_CONTROL'");
    }
    
    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.5);
        currentColor = controlPanel.getColorMatch();
        if(currentColor != previousColor && currentColor != ColorSensor.UNKNOWN && previousColor != ColorSensor.UNKNOWN)
        {
            totalWedges++;
            
        }
        SmartDashboard.putNumber("Num Wedges", totalWedges);
        System.out.println(totalWedges);
        previousColor = currentColor;
    }
    
    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
        System.out.println("disabling 'ROTATION_CONTROL");
    }
    
    @Override
    public boolean isFinished()
    {
        return totalWedges >= NUM_ROTATIONS * WEDGES_PER_ROTATION;
    }
}
