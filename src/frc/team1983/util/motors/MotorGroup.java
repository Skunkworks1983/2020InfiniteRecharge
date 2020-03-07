package frc.team1983.util.motors;

import frc.team1983.util.sensors.AnalogEncoder;
import frc.team1983.util.sensors.DigitalInputEncoder;
import frc.team1983.util.sensors.DutyCycleEncoder;
import frc.team1983.util.sensors.Encoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a system of motors and an encoder
 */
public class MotorGroup
{
    private Motor master;
    private ArrayList<Motor> slaves;

    private ArrayList<Motor> motors;

    private Encoder encoder;

    /**
     * Constructor for a motorGroup with a name, master, encoder, and other motors, regardless
     * of whether or not the motor controllers are Talons or Sparks.
     *
     * @param encoder The encoder of the system
     * @param master The master motor. Ensures that there is at least one motor, all other motors will follow the master
     * @param slaves An array of the other motors in this system. Can be left out if there is only one motor.
     */
    protected MotorGroup(Encoder encoder, Motor master, Motor... slaves)
    {
        this.encoder = encoder;
        this.master = master;
        this.slaves = new ArrayList<>();
        this.slaves.addAll(Arrays.asList(slaves));
        for (Motor slave : this.slaves)
            slave.follow(master);

        this.motors = new ArrayList<>();
        this.motors.add(master);
        this.motors.addAll(Arrays.asList(slaves));
    }

    /**
     * Constructor for a motorGroup where the master motor is also the encoder
     * Either a Talon with an encoder plugged in or a NEO with the built-in encoder
     *
     * @param master The master motor. Ensures that there is at least one motor, all other motors will follow the master
     * @param slaves An array of the other motors in this system. Can be left out if there is only one motor.
     */
    public MotorGroup(Motor master, Motor... slaves)
    {
        this((Encoder) master, master, slaves);
    }

    /**
     * Constructor for a motorGroup with an external encoder
     *
     * @param digitalInputEncoder An external digital input encoder
     * @param master The master motor. Ensures that there is at least one motor, all other motors will follow the master
     * @param slaves An array of the other motors in this system. Can be left out if there is only one motor.
     */
    public MotorGroup(DigitalInputEncoder digitalInputEncoder, Motor master, Motor... slaves)
    {
        this((Encoder) digitalInputEncoder, master, slaves);
    }

    public MotorGroup(AnalogEncoder analogEncoder, Motor master, Motor... slaves)
    {
        this((Encoder) analogEncoder, master, slaves);
    }

    public MotorGroup(DutyCycleEncoder dutyCycleEncoder, Motor master, Motor... slaves)
    {
        this((Encoder) dutyCycleEncoder, master, slaves);
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        encoder.zero();
    }

    /**
     * Set the motor output in a control mode
     *
     * @param controlMode The control mode the motor should run in
     * @param value The setpoint at which the motor should run
     */
    public void set(ControlMode controlMode, double value)
    {
        master.set(controlMode, value);
    }

    /**
     * @param brake If the motors should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        for (Motor motor : motors)
            motor.setBrake(brake);
    }

    public void setVoltageRamp(double value)
    {
        for (Motor motor : motors)
            motor.setVoltageRamp(value);
    }

    /**
     * @return Get the current position in encoder ticks, by default the CANEncoder returns the number of rotations
     */
    public double getPositionTicks()
    {
        return encoder.getPositionTicks();
    }

    /**
     * @return Get the current position
     */
    public double getPosition()
    {
        return encoder.getPosition();
    }

    /**
     * @return Current encoder velocity, by default the CANEncoder returns the number of rotations per minute
     */
    public double getVelocityTicks()
    {
        return encoder.getVelocityTicks();
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocity()
    {
        return encoder.getVelocity();
    }

    public double getConversionRatio()
    {
        return encoder.getConversionRatio();
    }

    public void setConversionRatio(double conversionRatio)
    {
        encoder.setConversionRatio(conversionRatio);
    }

    public Encoder getEncoder()
    {
        return encoder;
    }

    public Motor getMaster()
    {
        return master;
    }

    public ArrayList<Motor> getSlaves()
    {
        return slaves;
    }

    public ArrayList<Motor> getMotors()
    {
        return motors;
    }
}
