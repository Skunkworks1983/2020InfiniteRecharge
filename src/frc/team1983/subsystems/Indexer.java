package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.Spark;

public class Indexer
{
    public DigitalInput indexerHasBall;

    public Motor collectorTransfer, internal, shooterTransfer;

    public Indexer()
    {
        indexerHasBall = new DigitalInput(RobotMap.Indexer.SENSOR);

        collectorTransfer = new Spark(RobotMap.Indexer.COLLECTOR_TRANSFER, RobotMap.Indexer.COLLECTOR_TRANSFER_REVERSED);

        internal = new Spark(RobotMap.Indexer.INTERNAL_SHUTTLE, RobotMap.Indexer.INTERNAL_SHUTTLE_REVERSED);

        shooterTransfer = new Spark(RobotMap.Indexer.SHOOTER_TRANSFER, RobotMap.Indexer.SHOOTER_TRANSFER_REVERSED);
    }


}
