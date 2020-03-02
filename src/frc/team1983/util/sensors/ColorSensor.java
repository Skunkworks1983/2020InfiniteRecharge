package frc.team1983.util.sensors;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor implements Runnable
{
    public static final int UPDATE_RATE = 600;

    private static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private String colorString;
    private double colorConfidence;
    private double red, green, blue;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    public ColorSensor()
    {
        new Thread(this).start();
    }
    protected synchronized void init()
    {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    protected synchronized void execute()
    {
        Color detectedColor = m_colorSensor.getColor();

        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";
        } else if (match.color == kRedTarget) {
            colorString = "Red";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }

        colorConfidence = match.confidence;
        red = detectedColor.red;
        green = detectedColor.green;
        blue = detectedColor.blue;
    }

    public synchronized String getColor()
    {
        return colorString;
    }

    public synchronized double getConfidence()
    {
        return colorConfidence;
    }

    public synchronized double getRed()
    {
        return red;
    }

    public synchronized double getGreen()
    {
        return green;
    }

    public synchronized double getBlue()
    {
        return blue;
    }

    @Override
    public void run()
    {
        while(true)
        {
            init();
            execute();

            try
            {
                Thread.sleep((long) 1000.0 / UPDATE_RATE);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
