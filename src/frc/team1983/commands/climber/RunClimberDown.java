package frc.team1983.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Climber;

public class RunClimberDown extends InstantCommand
{
    public RunClimberDown(Climber climber)
    {
        super(() -> climber.setClimberPosition(Climber.position.retracted), climber);
    }

    public RunClimberDown()
    {
        this(Robot.getInstance().getClimber());
    }
}
