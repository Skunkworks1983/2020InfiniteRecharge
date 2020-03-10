package frc.team1983.commands.controlPanel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.sensors.ColorSensor;

public class PositionControl extends CommandBase
{
    private ControlPanel controlPanel;
    private ColorSensor colorSensor;

    public PositionControl(ControlPanel controlPanel, ColorSensor colorSensor)
    {
        this.controlPanel = controlPanel;
        this.colorSensor = colorSensor;
    }

    public PositionControl()
    {
        this(Robot.getInstance().getControlPanel(), Robot.getInstance().getColorSensor());
    }

    @Override
    public void execute()
    {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
            switch (gameData.charAt(0))
            {
                case 'B' :
                    if(!colorSensor.getColor().equals("Red"))
                        controlPanel.setRoller(ControlPanel.positionControlSpeed);
                    else
                        controlPanel.setRoller(0.0);
                    break;
                case 'G' :
                    if(!colorSensor.getColor().equals("Yellow"))
                        controlPanel.setRoller(ControlPanel.positionControlSpeed);
                    else
                        controlPanel.setRoller(0.0);
                    break;
                case 'R' :
                    if(!colorSensor.getColor().equals("Blue"))
                        controlPanel.setRoller(ControlPanel.positionControlSpeed);
                    else
                        controlPanel.setRoller(0.0);
                    break;
                case 'Y' :
                    if(!colorSensor.getColor().equals("Green"))
                        controlPanel.setRoller(ControlPanel.positionControlSpeed);
                    else
                        controlPanel.setRoller(0.0);
                    break;
                default :
                    controlPanel.setRoller(0.0);
                    break;
            }
        }
        else
        {
            controlPanel.setRoller(0.0);
        }
    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(0.0);
        controlPanel.setBrake(true);
    }
}
