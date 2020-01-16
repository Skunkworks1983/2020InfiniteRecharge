package frc.team1983.util.motors;

import com.revrobotics.CANSparkMax;
import frc.team1983.util.sensors.Encoder;

/**
 * Wrapper for CANSparkMax motor controller
 */
public class Spark extends com.revrobotics.CANSparkMax implements Motor, Encoder
{
    private boolean reversed;
    private double conversionRatio = 1;
    private double encoderOffset;

    /**
     * @param port The deviceID
     * @param reversed If the motor is reversed
     */
    public Spark(int port, boolean reversed)
    {
        super(port, MotorType.kBrushless);
        this.reversed = reversed;
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        encoderOffset = -getEncoder().getPosition();
    }

    /**
     * @param throttle Sets the percent output of the motor
     */
    public void set(double throttle)
    {
        super.set(reversed ? -throttle : throttle);
    }

    /**
     * @param brake If the motor should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        setIdleMode(brake ? IdleMode.kBrake : IdleMode.kCoast);
    }

    /**
     * @return Get the current position in encoder ticks, by default the CANEncoder returns the number of rotations
     */
    public double getPositionTicks()
    {
        return encoderOffset + getEncoder().getPosition();
    }

    /**
     * @return Get the current position
     */
    public double getPosition()
    {
        return getPositionTicks() * conversionRatio;
    }

    /**
     * @return Current encoder velocity, by default the CANEncoder returns the number of rotations per minute
     */
    public double getVelocityTicks()
    {
        return getEncoder().getVelocity();
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocity()
    {
        return getVelocityTicks() * conversionRatio;
    }

    public void follow(Motor leader)
    {
        super.follow((CANSparkMax) leader);
    }

    public double getConversionRatio()
    {
        return conversionRatio;
    }

    public void setConversionRatio(double conversionRatio)
    {
        this.conversionRatio = conversionRatio;
    }
}
