package frc.team1983.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;

public class ToggleControlPanel extends InstantCommand
{
    public ToggleControlPanel(ControlPanel controlPanel, boolean extended)
    {
        super(() -> controlPanel.setExtended(extended), controlPanel);
    }

    public ToggleControlPanel(boolean extended)
    {
        this(Robot.getInstance().getControlPanel(), extended);
    }
}
