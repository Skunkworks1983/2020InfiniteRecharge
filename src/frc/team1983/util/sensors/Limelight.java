package frc.team1983.util.sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight implements Runnable
{
    public enum PipelineSetting
    {
        PIPE_LINE_ZERO(0),
        PIPE_LINE_ONE(1);

        private int pipelineSetting;

        PipelineSetting(int pipelineSetting)
        {
            this.pipelineSetting = pipelineSetting;
        }

        public int getPipelineSetting()
        {
            return pipelineSetting;
        }

        public static PipelineSetting fromDouble(double value)
        {
            for (PipelineSetting pipelineSetting : PipelineSetting.values())
            {
                if (pipelineSetting.getPipelineSetting() == value)
                {
                    return pipelineSetting;
                }
            }

            return PIPE_LINE_ZERO;
        }
    }
    public enum LedMode
    {
        USE_PIPELINE(0),
        FORCE_OFF(1),
        FORCE_BLINK(2),
        FORCE_ON(3);

        private int ledMode;

        LedMode(int ledMode)
        {
            this.ledMode = ledMode;
        }

        public int getLedMode()
        {
            return ledMode;
        }

        public static LedMode fromDouble(double value)
        {
            for(LedMode ledMode : LedMode.values())
            {
                if(ledMode.getLedMode() == value)
                {
                    return ledMode;
                }
            }

            System.out.println("Led Mode not found from double: " + value);
            return DEFAULT_LED_MODE;
        }

    }

    public static final int UPDATE_RATE = 250;

    public static final boolean TARGET_DETECTED_DEFAULT_VALUE = false;
    public static final double X_DEFAULT_VALUE = 0.0;
    public static final double Y_DEFAULT_VALUE = 0.0;
    public static final double AREA_DEFAULT_VALUE = 0.0;
    public static final double SKEW_DEFAULT_VALUE = 0.0;
    public static final double X_OFFSET_DEFAULT_VALUE = 0.0;
    public static final double Y_OFFSET_DEFAULT_VALUE = 0.0;
    public static final double PITCH_DEFAULT_VALUE = 0.0;
    public static final double YAW_DEFAULT_VALUE = 0.0;
    public static final double ROLL_DEFAULT_VALUE = 0.0;
    public static final LedMode DEFAULT_LED_MODE = LedMode.FORCE_OFF;

    private boolean targetDetected;
    private double x, y;
    private double area;
    private double skew;
    private LedMode ledMode = DEFAULT_LED_MODE;
    private PipelineSetting pipelineSetting;
    private double xOffset, yOffset;
    private double pitch, yaw, roll;

    public Limelight()
    {
        new Thread(this).start();
    }

    protected synchronized void execute()
    {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

        // Update values
        targetDetected = table.getEntry("tv").getDouble(TARGET_DETECTED_DEFAULT_VALUE ? 1 : 0) == 1;
        x = table.getEntry("tx").getDouble(X_DEFAULT_VALUE);
        y = table.getEntry("ty").getDouble(Y_DEFAULT_VALUE);
        area = table.getEntry("ta").getDouble(AREA_DEFAULT_VALUE);
        skew = table.getEntry("ts").getDouble(SKEW_DEFAULT_VALUE);

        if(ledMode != LedMode.fromDouble(table.getEntry("ledMode").getDouble(DEFAULT_LED_MODE.getLedMode())))
        {
            setLedMode(ledMode);
        }
    }

    public boolean isTargetDetected()
    {
        return targetDetected;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getArea()
    {
        return area;
    }

    public double getSkew()
    {
        return skew;
    }

    public double getXOffset()
    {
        return xOffset;
    }

    public double getYOffset()
    {
        return yOffset;
    }

    public double getPitch()
    {
        return pitch;
    }

    public double getYaw()
    {
        return yaw;
    }

    public double getRoll()
    {
        return roll;
    }

    public LedMode getLedMode()
    {
        return ledMode;
    }

    public void setLedMode(LedMode ledMode)
    {
        this.ledMode = ledMode;
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledMode.getLedMode());
    }

    public void setPipelineSetting(PipelineSetting pipelineSetting)
    {
        this.pipelineSetting = pipelineSetting;
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipelineSetting.getPipelineSetting());
    }

    @Override
    public void run()
    {
        while(true)
        {
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
