package frc.team1983.util.sensors;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import frc.team1983.util.HSVColor;


public class ColorSensor extends ColorSensorV3
{
    public ColorSensor()
    {
        super(I2C.Port.kOnboard);
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
}