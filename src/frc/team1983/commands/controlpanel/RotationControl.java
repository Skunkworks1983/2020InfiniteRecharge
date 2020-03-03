package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.HSVColor;

public class RotationControl extends CommandBase
{
    //TODO add color sensor
    private static final double NUM_ROTATIONS = 3.5;
    private static final double WEDGES_PER_ROTATION = 8;
    
    private ControlPanel controlPanel;
    private HSVColor.Color previousColor;
    private HSVColor.Color currentColor;
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
        previousColor = controlPanel.getColor();
    }
    
    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.5);
        currentColor = controlPanel.getColor();
        if(currentColor != previousColor && currentColor != HSVColor.Color.UNIDENTIFIED && previousColor != HSVColor.Color.UNIDENTIFIED)
        {
            totalWedges++;
        }
        previousColor = currentColor;
    }
    
    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
    
    @Override
    public boolean isFinished()
    {
        return totalWedges >= NUM_ROTATIONS * WEDGES_PER_ROTATION;
    }
}
