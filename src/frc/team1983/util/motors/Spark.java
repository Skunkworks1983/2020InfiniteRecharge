package frc.team1983.util.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import frc.team1983.util.sensors.Encoder;

/**
 * Wrapper for CANSparkMax motor controller
 */
public class Spark extends com.revrobotics.CANSparkMax implements Motor, Encoder
{
    private double conversionRatio = 1;
    private double encoderOffset;
    private int port;

    /**
     * @param port The deviceID
     * @param reversed If the motor is reversed
     */
    public Spark(int port, boolean reversed)
    {
        super(port, MotorType.kBrushless);
        setInverted(reversed);
        this.port = port;
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        encoderOffset = -getEncoder().getPosition();
    }

    /**
     * Set the motor output in a control mode
     *
     * @param controlMode The control mode the motor should run in
     * @param value The setpoint at which the motor should run
     */
    public void set(ControlMode controlMode, double value)
    {
        switch (controlMode)
        {
            case Throttle:
                super.set(value);
                System.out.printf("Port: %d value %f\n", port,  value);
                break;
            case Position:
                super.getPIDController().setReference(value, ControlType.kPosition);
                break;
            case Velocity:
                super.getPIDController().setReference(value, ControlType.kVelocity);
                break;
        }
    }

    /**
     * @param brake If the motor should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        setIdleMode(brake ? IdleMode.kBrake : IdleMode.kCoast);
    }

    /**
     * @param rate How long motor should take to accelerate from 0 percent to 100 percent
     */
    public  void setVoltageRamp(double rate)
    {
        setOpenLoopRampRate(rate);
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
        super.follow((CANSparkMax) leader, ((CANSparkMax) leader).getInverted() != getInverted());
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
