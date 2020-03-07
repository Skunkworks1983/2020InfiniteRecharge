package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.HSVColor;
import frc.team1983.util.sensors.ColorSensor;

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
        if (getAssignedColor() != ColorSensor.ColorEnum.UNKNOWN)
            {
                controlPanel.setAssignedColor(getAssignedColor());
                controlPanel.setAlreadyPolled(true);
            }
    }
    
    public static char getAssignedColorChar()
    {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(0);
    }
    
    private static ColorSensor.ColorEnum getAssignedColor()
        {
            char assignedColorChar = getAssignedColorChar();
            if(assignedColorChar == 'R')
            {
                return ColorSensor.ColorEnum.RED;
            }
            else if(assignedColorChar == 'G')
            {
                return ColorSensor.ColorEnum.GREEN;
            }
            else if(assignedColorChar == 'B')
            {
                return ColorSensor.ColorEnum.BLUE;
            }
            else if(assignedColorChar == 'Y')
            {
                return ColorSensor.ColorEnum.YELLOW;
            }
            else
            {
                return ColorSensor.ColorEnum.UNKNOWN;
            }
        }
}
