package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;


public class SetCollectorPosition extends InstantCommand
{
    public SetCollectorPosition(Collector collector)
    {
        // basically a fancy if/else statement
        // ? is the comparison/ if
        // : is the then/else
        // it takes the ?, :, and collector within the super
        super(() -> collector.setCollectorPosition(!collector.getCollectorStatus()), collector);
    }
    public SetCollectorPosition()
    {
        this(Robot.getInstance().getCollector());
    }
}
