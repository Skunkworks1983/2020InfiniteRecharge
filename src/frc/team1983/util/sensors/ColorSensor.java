package frc.team1983.util.sensors;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.team1983.util.HSVColor;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor extends ColorSensorV3
{
    public static final char YELLOW = 'Y';
    public static final char RED = 'R';
    public static final char GREEN = 'G';
    public static final char BLUE = 'B';
    public static final char UNKNOWN = 'U';
    
    private final ColorMatch colorMatch = new ColorMatch();
    
    private final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113); //Percentage of component of light (0 - 1)
    
    public ColorSensor()
    {
        super(I2C.Port.kOnboard.kOnboard);
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
    
    public char getColorMatch()
    {
        char colorChar;
        ColorMatchResult match = colorMatch.matchClosestColor(getRGBColor());
        
        if (match.color == BLUE_TARGET) {
            colorChar = BLUE;
        } else if (match.color == RED_TARGET) {
            colorChar = RED;
        } else if (match.color == GREEN_TARGET) {
            colorChar = GREEN;
        } else if (match.color == YELLOW_TARGET) {
            colorChar = YELLOW;
        } else {
            colorChar = UNKNOWN;
        }
        return colorChar;
    }
}
