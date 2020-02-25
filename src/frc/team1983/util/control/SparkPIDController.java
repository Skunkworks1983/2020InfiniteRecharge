package frc.team1983.util.control;

import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Spark;

/**
 * Wrapper for CANPIDController
 */
public class SparkPIDController extends CANPIDController
{
    private Spark spark;

    /**
     * @param spark The Spark this object configures
     */
    public SparkPIDController(Spark spark)
    {
        super(spark);
        this.spark = spark;
    }

    /**
     * Set the controller reference value based on the selected control mode
     *
     * @param controlMode The control mode the controller should control in
     * @param value What value to set the controller to
     * @param feedForward Feed forward to apply to the controller
     */
    public void setSetpoint(ControlMode controlMode, double value, double feedForward)
    {
        switch (controlMode)
        {
            case Throttle:
                setReference(value / 12.0, ControlType.kVoltage, 0, feedForward);
                break;
            case Position:
                setReference(value * spark.getConversionRatio(), ControlType.kPosition, 0, feedForward);
                break;
            case Velocity:
                setReference(value * spark.getConversionRatio(), ControlType.kVelocity, 0, feedForward);
                break;
        }
    }

    /**
     * Set the controller reference value based on the selected control mode
     *
     * @param controlMode The control mode the controller should control in
     * @param value What value to set the controller to
     */
    public void setSetpoint(ControlMode controlMode, double value)
    {
        setSetpoint(controlMode, value, 0);
    }

    public void setGains(double kP, double kI, double kD, double kF)
    {
        super.setP(kP);
        super.setI(kI);
        super.setD(kD);
        super.setFF(kF);

        spark.burnFlash();
    }

    public void setGains(double kP, double kI, double kD)
    {
        setGains(kP, kI, kD, 0.0);
    }

    public Spark getSpark()
    {
        return spark;
    }
}