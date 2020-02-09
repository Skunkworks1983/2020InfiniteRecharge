package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.*;
import frc.team1983.commands.TargetAlignment;

public class RendezvousToTrench extends SequentialCommandGroup
{
	public RendezvousToTrench()
	{
		addCommands(
			new StartInFrontOfTrenchRunToRendezvousBall4And5(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5ToRendezvousTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchThroughTrenchRun()
		);
	}
}
