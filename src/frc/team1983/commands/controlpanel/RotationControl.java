package frc.team1983.commands.controlpanel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.subsystems.ControlPanel;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.HSVColor;
import frc.team1983.util.sensors.ColorSensor;

import javax.naming.ldap.Control;
import java.util.Arrays;

public class RotationControl extends CommandBase
{
    private static final double NUM_ROTATIONS = 1;
    private static final double WEDGES_PER_ROTATION = 8;
    private static final double ROLLER_SPEED = 0.25;
    
    private ControlPanel controlPanel;
    private ColorSensor.ColorEnum previousColor;
    private ColorSensor.ColorEnum nextColor;
    private double totalWedges;
    private static final ColorSensor.ColorEnum[] COLORS = {ColorSensor.ColorEnum.YELLOW, ColorSensor.ColorEnum.RED, ColorSensor.ColorEnum.GREEN, ColorSensor.ColorEnum.BLUE};
    
    public RotationControl(ControlPanel controlPanel)
    {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }
    
    public RotationControl()
    {
        this(Robot.getInstance().getControlPanel());
    }
    
    @Override
    public void initialize()
    {
        previousColor = controlPanel.getColorMatch();
        System.out.println("initializing 'ROTATION_CONTROL'");
    }
    
    @Override
    public void execute()
    {
        controlPanel.setRoller(ControlMode.Throttle, ROLLER_SPEED);
        nextColor = COLORS[(Arrays.binarySearch(COLORS, previousColor) + 1) % 4];
        if(controlPanel.getColorMatch() == nextColor)
        {
            totalWedges++;
            previousColor = nextColor;
            System.out.println(nextColor.name());
        }
    }
    
    @Override
    public void end(boolean interrupted)
    {
        controlPanel.setRoller(ControlMode.Throttle, 0.0);
        System.out.println("disabling 'ROTATION_CONTROL'");
    }
    
    @Override
    public boolean isFinished()
    {
        return totalWedges >= NUM_ROTATIONS * WEDGES_PER_ROTATION;
    }
}
