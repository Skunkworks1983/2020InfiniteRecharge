package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.RendezvousBall4And5ToRendezvousTrenchRunSwitch;
import frc.team1983.autonomous.paths.RendezvousTrenchRunSwitchToTrenchRunBall4And5;
import frc.team1983.autonomous.paths.StartInFrontOfTrenchRunToRendezvousBall4And5;
import frc.team1983.commands.TargetAlignment;

public class InFrontOfTrenchRunToRendezvousPointToTrenchRun extends SequentialCommandGroup
{
	public InFrontOfTrenchRunToRendezvousPointToTrenchRun()
	{
		addCommands(
			new StartInFrontOfTrenchRunToRendezvousBall4And5(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5ToRendezvousTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchToTrenchRunBall4And5()
		);
	}
}
