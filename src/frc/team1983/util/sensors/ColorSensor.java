package frc.team1983.util.sensors;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.team1983.util.HSVColor;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor extends ColorSensorV3
{
    public enum ColorEnum
    {
        YELLOW,
        RED,
        GREEN,
        BLUE,
        UNKNOWN
    }
    
    private static final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private static final Color GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private static final Color RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private static final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113); //Percentage of component of light (0 - 1)
    
    private final ColorMatch colorMatch = new ColorMatch();
    
    public ColorSensor()
    {
        super(I2C.Port.kOnboard);
        colorMatch.addColorMatch(BLUE_TARGET);
        colorMatch.addColorMatch(GREEN_TARGET);
        colorMatch.addColorMatch(RED_TARGET);
        colorMatch.addColorMatch(YELLOW_TARGET);
    }
    
    public Color getRGBColor()
    {
        return super.getColor();
    }
    
    public HSVColor getHSVColor()
    {
        edu.wpi.first.wpilibj.util.Color wpiColor = super.getColor();
        return new HSVColor(java.awt.Color.RGBtoHSB((int) (wpiColor.red * 255), (int) (wpiColor.green * 255), (int) (wpiColor.blue * 255), null));
    }
    
    public ColorEnum getColorMatch()
    {
        ColorMatchResult match = colorMatch.matchClosestColor(getRGBColor());
        
        if(match.color == BLUE_TARGET)
        {
            return ColorEnum.BLUE;
        }
        else if(match.color == RED_TARGET)
        {
            return ColorEnum.RED;
        }
        else if(match.color == GREEN_TARGET)
        {
            return ColorEnum.GREEN;
        }
        else if(match.color == YELLOW_TARGET)
        {
            return ColorEnum.YELLOW;
        }
        else
        {
            return ColorEnum.UNKNOWN;
        }
    }
    public double getColorMatchConfidence()
    {
        return colorMatch.matchClosestColor(getRGBColor()).confidence;
    }
}
