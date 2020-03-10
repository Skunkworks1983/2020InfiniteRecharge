package frc.team1983.commands.controlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;

public class RotationControl extends CommandBase
{
    private ControlPanel controlPanel;

    public RotationControl(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
    }

    public RotationControl()
    {
        this(Robot.getInstance().getControlPanel());
    }

    @Override
    public void initialize()
    {
        controlPanel.zero();
    }
    @Override
    public void execute()
    {
        if(controlPanel.getPosition() < ControlPanel.targetDistance)
            controlPanel.setRoller(ControlPanel.rotationControlSpeed);
        else
            controlPanel.setRoller(0.0);
    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(0.0);
        controlPanel.setBrake(true);
    }
}
