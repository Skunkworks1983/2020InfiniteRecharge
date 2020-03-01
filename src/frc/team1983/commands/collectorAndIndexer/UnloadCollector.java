package frc.team1983.commands.collectorAndIndexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1983.Robot;
import frc.team1983.services.OI;
import frc.team1983.subsystems.Collector;
import frc.team1983.util.motors.ControlMode;

public class UnloadCollector extends CommandBase
{
    private Collector collector;
    private OI oi;
    private ControlMode controlMode;
    private double collectorValue;

    public UnloadCollector(Collector c, ControlMode cm, double v)
    {
        collector = c;
        collectorValue = v;
        controlMode = cm;
    }

    public UnloadCollector(ControlMode controlMode, double collectorValue)
    {
        this(Robot.getInstance().getCollector(), controlMode, collectorValue);
    }

    @Override //when we have motors reversed, then they're always in initialize
    public void initialize()
    {
        collector.setCollectorMotor(Collector.motorsReversed);
    }

    @Override
    public void execute() //TODO: do not test without fixing reversed values in robotmap
    {

    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        collector.setCollectorMotor(Collector.motorsOff);
    }
}
