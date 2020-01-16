package frc.team1983.util.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team1983.util.sensors.Encoder;

/**
 * Wrapper for TalonSRX motor controller
 */
public class Talon extends com.ctre.phoenix.motorcontrol.can.TalonSRX implements Motor, Encoder
{
    private boolean reversed;
    private double conversionRatio = 1;
    private double encoderOffset;

    /**
     * @param port The deviceID
     * @param reversed If the motor is reversed
     * @param hasEncoder If there is an encoder
     */
    public Talon(int port, boolean reversed, boolean hasEncoder)
    {
        super(port);
        this.reversed = reversed;

        if (hasEncoder)
        {
            configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        }
    }

    /**
     * @param port The deviceID
     * @param reversed If the motor is reversed
     */
    public Talon(int port, boolean reversed)
    {
        this(port, reversed, false);
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        encoderOffset = getSelectedSensorPosition();
    }

    /**
     * @param throttle Sets the percent output of the motor
     */
    public void set(double throttle)
    {
        super.set(ControlMode.PercentOutput, reversed ? -throttle : throttle);
    }

    /**
     * @param brake If the motor should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        setNeutralMode(brake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    /**
     * @return Get the current position in encoder ticks
     */
    public double getPositionTicks()
    {
        return encoderOffset + getSelectedSensorPosition();
    }

    /**
     * @return Get the current position
     */
    public double getPosition()
    {
        return getPositionTicks() * conversionRatio;
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocityTicks()
    {
        return getSelectedSensorVelocity();
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocity()
    {
        return getVelocityTicks() * conversionRatio;
    }

    @Override
    public void follow(Motor leader)
    {
        super.follow((TalonSRX) leader);
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
