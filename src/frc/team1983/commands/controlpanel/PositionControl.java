package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.HSVColor;

public class PositionControl extends CommandBase
{
    private static int NUM_COLORS = 4;
    private static double ROLLER_SPEED = 0.5;
    private ControlPanel controlPanel;
    private HSVColor.Color initialColor;
    private HSVColor.Color assignedColor;
    private HSVColor.Color targetColor;
    private int distance; //measured in wedges
    private boolean isReversed;
    private final String[] COLORS = {"YELLOW", "RED", "GREEN", "BLUE"};

    public PositionControl(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
    }

    public PositionControl()
    {
        this(Robot.getInstance().getControlPanel());
    }
    
    @Override
    public void initialize()
    {
        initialColor = controlPanel.getColor();
        assignedColor = controlPanel.getAssignedColor();
        targetColor = HSVColor.Color.valueOf(COLORS[(assignedColor.getColor() + 2) % NUM_COLORS]);
        distance = (NUM_COLORS + targetColor.getColor() - initialColor.getColor()) % NUM_COLORS;
        isReversed = distance > 2;
    }
    
    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, ROLLER_SPEED * (isReversed ? -1 : 1));
    }
    
    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
    }
    
    @Override
    public boolean isFinished()
    {
        return controlPanel.getAssignedColor() == HSVColor.Color.UNIDENTIFIED || controlPanel.getColor() == targetColor;
    }
}
