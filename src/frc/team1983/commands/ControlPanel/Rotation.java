package frc.team1983.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;

public class Rotation extends CommandBase
{
    private ControlPanel controlPanel;
    private ControlMode controlMode;
    private double value;

    public Rotation(ControlPanel controlPanel, ControlMode controlMode, double value)
    {
        this.controlPanel = controlPanel;
        this.controlMode = controlMode;
        this.value = value;
    }

    public Rotation(ControlMode controlMode, double value)
    {
        this(Robot.getInstance().getControlPanel(), controlMode, value);
    }

    @Override
    public void execute()
    {
        //TODO find actual position value
        controlPanel.setRoller(ControlMode.Position, 254);
    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
}
