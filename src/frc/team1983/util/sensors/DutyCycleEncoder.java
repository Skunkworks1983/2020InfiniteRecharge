package frc.team1983.util.sensors;

public class DutyCycleEncoder extends edu.wpi.first.wpilibj.DutyCycleEncoder implements Encoder, Runnable
{
    private static final int UPDATE_RATE = 250;

    private double conversionRatio = 1;
    private double encoderOffset;

    private double velocity;

    private double prevPos;
    private long prevTime;

    public DutyCycleEncoder(int channel)
    {
        super(channel);
        new Thread(this).start();
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {

    }

    /**
     * @return Get the current position in encoder ticks
     */
    public double getPositionTicks()
    {
        return encoderOffset + get();
    }

    /**
     * @return Get the current position
     */
    public double getPosition()
    {
        return getPositionTicks() * conversionRatio;
    }

    /**
     * @return Current encoder velocity in encoder ticks
     */
    public double getVelocityTicks()
    {
        return velocity;
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocity()
    {
        return getVelocityTicks() * conversionRatio;
    }

    public double getConversionRatio()
    {
        return conversionRatio;
    }

    public void setConversionRatio(double conversionRatio)
    {
        this.conversionRatio = conversionRatio;
    }

    public void run()
    {
        while(true)
        {
            double currentPos = getPosition();
            long currentTime = System.currentTimeMillis();

            //(double) casts ints to doubles, preventing integer division
            velocity = (((double) (currentPos - prevPos)) / ((double) (currentTime - prevTime)));

            prevPos = currentPos;
            prevTime = currentTime;

            try
            {
                Thread.sleep((long) 1000.0 / UPDATE_RATE);
            }
            catch(InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }
    }
}