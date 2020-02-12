package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;


public class SetCollectorPosition extends InstantCommand
{
    public SetCollectorPosition(Collector collector, boolean extended)
    {
        super(() -> collector.setCollectorPosition(Collector.position.extended), collector);
    }
    public SetCollectorPosition(boolean extended)
    {
        this(Robot.getInstance().getCollector(), extended);
    }



//    private Collector collector;
//    private Collector.position position;
//
//    public SetCollectorPosition(Collector collect, Collector.position p)
//    {
//        collector = collect;
//        position = p;
//    }
//
//    @Override
//    public void initialize()
//    {
//        collector.setCollectorPosition(this.position);
//    }
//
//    @Override
//    public void execute() //TODO: do not test without fixing reversed values in robotmap
//    {
//
//    }
//
//    @Override
//    public boolean isFinished()
//    {
//        return true;
//    }
//
//    @Override
//    public void end(boolean interrupted)
//    {
//
//    }
}
