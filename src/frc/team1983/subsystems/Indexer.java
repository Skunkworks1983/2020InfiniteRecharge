package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.ControlMode;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.Spark;

import javax.naming.ldap.Control;

public class Indexer
{
    public DigitalInput indexerHasBall;

    public double voltageRamp = 1;

    private Motor collectorTransfer, shooterTransfer, internal;

    // used in indexer so that we have a constant for motor directions
    public static double motorsForward = .5;
    public static double motorsReversed = -0.5;
    public static double motorsOff = 0;
    public static double internalForward = 0.7;
    public static double internalReversed = -0.7;

    public Indexer()
    {
        indexerHasBall = new DigitalInput(RobotMap.Indexer.SENSOR);

        collectorTransfer = new Spark(RobotMap.Indexer.COLLECTOR_TRANSFER,
                RobotMap.Indexer.COLLECTOR_TRANSFER_REVERSED);

        shooterTransfer = new Spark(RobotMap.Indexer.SHOOTER_TRANSFER, RobotMap.Indexer.SHOOTER_TRANSFER_REVERSED);

        internal = new Spark(RobotMap.Indexer.INTERNAL, RobotMap.Indexer.INTERNAL_REVERSED);
    }

    public void setVoltageRamp(double volts)
    {
        internal.setVoltageRamp(volts);
    }

    public void setCollectorTransfer(double speed)
    {
        collectorTransfer.set(ControlMode.Throttle, speed);
    }

    public void setShooterTransfer(double speed)
    {
        shooterTransfer.set(ControlMode.Throttle, speed);
    }

    public void setInternal(double speed)
    {
        internal.set(ControlMode.Throttle, speed);
    }
}
