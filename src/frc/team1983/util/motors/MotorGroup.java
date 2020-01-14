package frc.team1983.util.motors;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a system of motors
 */
public class MotorGroup
{
    private Motor master;
    private ArrayList<Motor> slaves;

    private ArrayList<Motor> motors;

    /**
     * Constructor for a motorGroup with a name, master, encoder, and other motors, regardless
     * of whether or not the motor controllers are Talons or Sparks.
     *
     * @param master The master motor. Ensures that there is at least one motor, all other motors will follow the master
     * @param motors An array of the other motors in this system. Can be left out if there is only one motor.
     */
    public MotorGroup(Motor master, Motor... motors)
    {
        this.master = master;
        this.slaves = new ArrayList<>();
        this.slaves.addAll(Arrays.asList(motors));
        for (Motor slave : slaves)
            slave.follow(master);

        this.motors = new ArrayList<>();
        this.motors.add(master);
        this.motors.addAll(Arrays.asList(motors));
    }

    /**
     * Reset the encoder offset so that it reads zero at its current position
     */
    public void zero()
    {
        for (Motor motor : motors)
            motor.zero();
    }

    /**
     * @param throttle Sets the percent output of the motors, slaves follow the master motor throttle
     */
    public void set(double throttle)
    {
        master.set(throttle);
    }

    /**
     * @param brake If the motors should be in brake mode
     */
    public void setBrake(boolean brake)
    {
        for (Motor motor : motors)
            motor.setBrake(brake);
    }

    /**
     * @return Get the current position in encoder ticks, by default the CANEncoder returns the number of rotations
     */
    public double getPositionTicks()
    {
        return master.getPositionTicks();
    }

    /**
     * @return Get the current position
     */
    public double getPosition()
    {
        return master.getPosition();
    }

    /**
     * @return Current encoder velocity, by default the CANEncoder returns the number of rotations per minute
     */
    public double getVelocityTicks()
    {
        return master.getVelocityTicks();
    }

    /**
     * @return Current encoder velocity
     */
    public double getVelocity()
    {
        return master.getVelocity();
    }

    public double getConversionRatio()
    {
        return master.getConversionRatio();
    }

    public void setConversionRatio(double conversionRatio)
    {
        for (Motor motor : motors)
            motor.setConversionRatio(conversionRatio);
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
