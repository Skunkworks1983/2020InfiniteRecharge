package frc.team1983.util;

public class HSVColor
{
    public enum Color
    {
        YELLOW(0, 0, 0, 0, 0, 0, 0),
        RED(1, 0, 0, 0, 0, 0, 0),
        GREEN(2, 0, 0, 0, 0, 0, 0),
        BLUE(3, 0, 0, 0, 0, 0, 0),
        UNIDENTIFIED(4, 0, 0, 0, 0, 0, 0); //TODO threshold the color sensor/figure out what values equate to color on the wheel
        
        private double minH, minS, minV;
        private double maxH, maxS, maxV;
        private int color;
        
        Color(int color, double minH, double maxH, double minS, double maxS, double minV, double maxV)
        {
            this.minH = minH;
            this.maxH = maxH;
            this.minS = minS;
            this.maxS = maxS;
            this.minV = minV;
            this.maxV = maxV;
            this.color = color;
        }
    
        public double getMinH()
        {
            return minH;
        }
    
        public double getMaxH()
        {
            return maxH;
        }
    
        public double getMinS()
        {
            return minS;
        }
    
        public double getMaxS()
        {
            return maxS;
        }
    
        public double getMinV()
        {
            return minV;
        }
    
        public double getMaxV()
        {
            return maxV;
        }
    
        public int getColor()
        {
            return color;
        }
    }
    
    private double h, s, v;
    
    public HSVColor(double h, double s, double v)
    {
        this.h = h;
        this.s = s;
        this.v = v;
    }
    
    public HSVColor(float[] values)
    {
        this(values[0], values[1], values[2]);
    }
    
    public double getH()
    {
        return h;
    }
    
    public double getS()
    {
        return s;
    }
    
    public double getV()
    {
        return v;
    }
    
    public HSVColor.Color getColor()
    {
        if (h >= Color.BLUE.minH && h <= Color.BLUE.maxH && s >= Color.BLUE.minS && s <= Color.BLUE.maxS && v >= Color.BLUE.minV && v <= Color.BLUE.maxV)
        {
            return Color.BLUE;
        }
        else if (h >= Color.RED.minH && h <= Color.RED.maxH && s >= Color.RED.minS && s <= Color.RED.maxS && v >= Color.RED.minV && v <= Color.RED.maxV)
        {
            return Color.RED;
        }
        else if (h >= Color.GREEN.minH && h <= Color.GREEN.maxH && s >= Color.GREEN.minS && s <= Color.GREEN.maxS && v >= Color.GREEN.minV && v <= Color.GREEN.maxV)
        {
            return Color.GREEN;
        }
        else if (h >= Color.YELLOW.minH && h <= Color.YELLOW.maxH && s >= Color.YELLOW.minS && s <= Color.YELLOW.maxS && v >= Color.YELLOW.minV && v <= Color.YELLOW.maxV)
        {
            return Color.YELLOW;
        }
        else
        {
            return Color.UNIDENTIFIED;
        }
    }
    
    public static boolean equals(HSVColor color1, HSVColor color2)
    {
        if(color1.getColor() == Color.UNIDENTIFIED || color2.getColor() == Color.UNIDENTIFIED)
        {
            return false;
        }
        return color1.getColor() == color2.getColor();
    }
    
    public boolean equals(HSVColor other)
    {
        return equals(this, other);
    }
}
