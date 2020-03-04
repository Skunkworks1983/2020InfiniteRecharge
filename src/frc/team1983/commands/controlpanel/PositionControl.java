package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.HSVColor;
import frc.team1983.util.sensors.ColorSensor;

import java.util.Arrays;

public class PositionControl extends CommandBase
{
    private static int NUM_COLORS = 4;
    private static double ROLLER_SPEED = 0.5;
    private ControlPanel controlPanel;
    private char initialColor;
    private char assignedColor;
    private char targetColor;
    private int distance; //measured in wedges
    private boolean isReversed;
    private final char[] COLORS = {ColorSensor.YELLOW, ColorSensor.RED, ColorSensor.GREEN, ColorSensor.BLUE};

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
        initialColor = controlPanel.getColorMatch();
        assignedColor = controlPanel.getAssignedColor();
        targetColor = COLORS[(Arrays.binarySearch(COLORS, assignedColor) + 2) % NUM_COLORS];
        distance = (NUM_COLORS + Arrays.binarySearch(COLORS, targetColor) - Arrays.binarySearch(COLORS, initialColor)) % NUM_COLORS;
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
        return controlPanel.getAssignedColor() == ColorSensor.UNKNOWN || controlPanel.getAssignedColor() == targetColor;
    }
}
