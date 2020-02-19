package frc.team1983.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team1983.Robot;
import frc.team1983.subsystems.Climber;

public class RunClimberUp extends InstantCommand
{
    public RunClimberUp(Climber climber)
    {
        super(() -> climber.setClimberPosition(Climber.position.extended), climber);
    }

    public RunClimberUp()
    {
        this(Robot.getInstance().getClimber());
    }
}
