package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.ColorSensor;

public class Position extends CommandBase
{
    private ControlPanel controlPanel;
    private ColorSensor colorSensor;

    public Position(ControlPanel controlPanel, ColorSensor colorSensor)
    {
        this.controlPanel = controlPanel;
        this.colorSensor = colorSensor;
    }

    public Position()
    {
        this(Robot.getInstance().getControlPanel(), Robot.getInstance().getColorSensor());
    }

    @Override
    public void execute()
    {
        //TODO Figure out how to do position

    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
}
