package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.Spark;

public class Indexer
{
    public DigitalInput indexerHasBall;

    public Motor collectorTransfer, shooterTransfer;

    // used in indexer so that we have a constant for motor directions
    public static double motorsForward = 1;
    public static double motorsReversed = -1;
    public static double motorsOff = 0;

    public Indexer()
    {
        indexerHasBall = new DigitalInput(RobotMap.Indexer.SENSOR);

        collectorTransfer = new Spark(RobotMap.Indexer.COLLECTOR_TRANSFER, RobotMap.Indexer.COLLECTOR_TRANSFER_REVERSED);

        shooterTransfer = new Spark(RobotMap.Indexer.SHOOTER_TRANSFER, RobotMap.Indexer.SHOOTER_TRANSFER_REVERSED);
    }


}
