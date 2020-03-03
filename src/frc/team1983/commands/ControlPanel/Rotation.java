package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;

public class Rotation extends CommandBase
{
    private ControlPanel controlPanel;

    public Rotation(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
    }

    public Rotation()
    {
        this(Robot.getInstance().getControlPanel());
    }

    @Override
    public void execute()
    {
//        if(controlPanel.getPosition() < ControlPanel.targetDistance)
//            controlPanel.setRoller(ControlMode.Throttle, ControlPanel.controlPanelForward);
//        else
//            end(true);
    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
}
