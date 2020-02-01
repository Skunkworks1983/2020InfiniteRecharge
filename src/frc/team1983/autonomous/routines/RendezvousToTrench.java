package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.*;

public class RendezvousToTrench extends SequentialCommandGroup
{
	public RendezvousToTrench()
	{
		addCommands(
			new StartInFrontOfTrenchRunToRendezvousBall2(),
			new RendezvousBall2ToRendezvousTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchThroughTrenchRun()
		);
	}
}
