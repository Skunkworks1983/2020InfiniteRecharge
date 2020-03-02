package frc.team1983.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;

public class ManualRotation extends CommandBase
{
    private ControlPanel controlPanel;
    private double throttle;

    public ManualRotation(ControlPanel controlPanel, double throttle)
    {
        this.controlPanel = controlPanel;
        this.throttle = throttle;
    }

    public ManualRotation(double throttle)
    {
        this(Robot.getInstance().getControlPanel(), throttle);
    }

    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, throttle);
    }

    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
}
