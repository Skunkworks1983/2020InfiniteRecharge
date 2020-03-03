package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.util.sensors.Limelight;

public class SetPipelineSetting extends InstantCommand
{
    SetPipelineSetting(Limelight limelight, Limelight.PipelineSetting pipelineSetting)
    {
        super(() -> limelight.setPipelineSetting(pipelineSetting));
    }

    public SetPipelineSetting(Limelight.PipelineSetting pipelineSetting){
        this(Robot.getInstance().getLimelight(), pipelineSetting);
    }
}
