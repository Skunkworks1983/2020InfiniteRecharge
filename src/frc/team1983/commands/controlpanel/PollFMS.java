package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.HSVColor;

public class PollFMS extends InstantCommand
{
    DriverStation driverStation;
    ControlPanel controlPanel;
    
    public PollFMS(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
    }
    
    public PollFMS()
    {
        this(Robot.getInstance().getControlPanel());
    }
    
    @Override
    public void initialize()
    {
        if(controlPanel.isAlreadyPolled())
        {
            if (getAssignedColor() != HSVColor.Color.UNIDENTIFIED)
            {
                controlPanel.setAssignedColor(getAssignedColor());
                controlPanel.setAlreadyPolled(true);
            }
        }
    }
    
    public static char getAssignedColorChar()
    {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(0);
    }
    
    public static HSVColor.Color getAssignedColor()
    {
        char assignedColorChar = getAssignedColorChar();
        if(assignedColorChar == 'R')
        {
            return HSVColor.Color.RED;
        }
        else if(assignedColorChar == 'G')
        {
            return HSVColor.Color.GREEN;
        }
        else if(assignedColorChar == 'B')
        {
            return HSVColor.Color.BLUE;
        }
        else if(assignedColorChar == 'Y')
        {
            return HSVColor.Color.YELLOW;
        }
        else
        {
            return HSVColor.Color.UNIDENTIFIED;
        }
    }
}
