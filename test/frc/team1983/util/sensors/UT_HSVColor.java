package frc.team1983.util.sensors;

import frc.team1983.util.HSVColor;
import org.junit.Assert;
import org.junit.Test;

public class UT_HSVColor
{
    @Test
    public void colorsAreEqual()
    {
        Assert.assertTrue(HSVColor.Color.YELLOW == HSVColor.Color.YELLOW);
    }
    
    @Test
    public void colorsAreNotEqual()
    {
        Assert.assertFalse(HSVColor.Color.YELLOW == HSVColor.Color.RED);
    }
}
