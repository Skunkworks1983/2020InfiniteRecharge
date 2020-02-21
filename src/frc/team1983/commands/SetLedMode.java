package frc.team1983.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.util.sensors.Limelight;

public class SetLedMode extends InstantCommand
{
    public SetLedMode(Limelight limelight, Limelight.LedMode ledMode)
    {
        super(() -> limelight.setLedMode(ledMode));
    }

    public SetLedMode(Limelight.LedMode ledMode)
    {
        this(Robot.getInstance().getLimelight(), ledMode);
    }
}
