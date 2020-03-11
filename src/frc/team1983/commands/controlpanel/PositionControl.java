package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.sensors.ColorSensor;

import java.util.Arrays;


public class PositionControl extends CommandBase
{
    private static int NUM_COLORS = 4;
    private static double ROLLER_SPEED = 0.15;
    private ControlPanel controlPanel;
    private ColorSensor.ColorEnum targetColor;
    private int wedgeCount; //measured in wedges
    private boolean isReversed;
    private static final ColorSensor.ColorEnum[] COLORS = {ColorSensor.ColorEnum.YELLOW, ColorSensor.ColorEnum.RED, ColorSensor.ColorEnum.GREEN, ColorSensor.ColorEnum.BLUE};

    public PositionControl(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
    }

    public PositionControl()
    {
        this(Robot.getInstance().getControlPanel());
    }
    
    public PositionControl(ColorSensor.ColorEnum colorOverride)
    {
        this(Robot.getInstance().getControlPanel());
        controlPanel.setAlreadyPolled(true);
        controlPanel.setAssignedColor(colorOverride);
    }
    
    @Override
    public void initialize()
    {
        System.out.println("Initializing 'PositionControl'");
        targetColor = COLORS[(Arrays.binarySearch(COLORS, controlPanel.getAssignedColor()) + 2) % NUM_COLORS];
        wedgeCount = (NUM_COLORS + Arrays.binarySearch(COLORS, targetColor) - Arrays.binarySearch(COLORS, controlPanel.getColorMatch())) % NUM_COLORS;
        isReversed = wedgeCount > 2;
    }
    
    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, ROLLER_SPEED * (isReversed ? -1 : 1));
    }
    
    @Override
    public boolean isFinished()
    {
        return controlPanel.getAssignedColor() == ColorSensor.ColorEnum.UNKNOWN || controlPanel.getColorMatch() == targetColor;
    }
    
    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
        controlPanel.setBrakeMode(true);
        System.out.println("Ending 'PositionControl'");
    }
}