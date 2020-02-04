package frc.team1983.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team1983.constants.RobotMap;
import frc.team1983.util.motors.Motor;
import frc.team1983.util.motors.MotorGroup;
import frc.team1983.util.motors.Spark;

public class Indexer
{
    private DigitalInput bottomHasBall;
    private DigitalInput middleHasBall;
    private DigitalInput topHasBall;

    private Motor collector, internal, shooter;

    public Indexer()
    {
        bottomHasBall = new DigitalInput(RobotMap.Indexer.BOTTOMSENSOR);
        middleHasBall = new DigitalInput(RobotMap.Indexer.MIDDLESENSOR);
        topHasBall = new DigitalInput(RobotMap.Indexer.TOPSENSOR);

        collector = new Spark(RobotMap.Indexer.COLLECTOR_TRANSFER, RobotMap.Indexer.COLLECTOR_TRANSFER_REVERSED);

        internal = new Spark(RobotMap.Indexer.INTERNAL_SHUTTLE, RobotMap.Indexer.INTERNAL_SHUTTLE_REVERSED);

        shooter = new Spark(RobotMap.Indexer.SHOOTER_TRANSFER, RobotMap.Indexer.SHOOTER_TRANSFER_REVERSED);
    }

    public void runIndexer()
    {
        
    }
}
