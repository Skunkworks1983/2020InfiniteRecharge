package frc.team1983.commands.controlPanel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;

public class ToggleControlPanel extends InstantCommand
{
    public ToggleControlPanel(ControlPanel controlPanel)
    {
        super(() -> controlPanel.setExtended(!controlPanel.getFolded()), controlPanel);
    }

    public ToggleControlPanel()
    {
        this(Robot.getInstance().getControlPanel());
    }
}
