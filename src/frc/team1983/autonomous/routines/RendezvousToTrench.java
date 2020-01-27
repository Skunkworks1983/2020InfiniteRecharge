package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.RendezvousBall1And2ToRendezvousTrenchRunSwitch;
import frc.team1983.autonomous.paths.RendezvousTrenchRunSwitchThroughTrenchRun;
import frc.team1983.autonomous.paths.StartInFrontOfTrenchRunToRendezvousBall1And2;

public class RendezvousToTrench extends SequentialCommandGroup
{
	public RendezvousToTrench()
	{
		addCommands(
			new StartInFrontOfTrenchRunToRendezvousBall1And2(),
			new RendezvousBall1And2ToRendezvousTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchThroughTrenchRun()
		);
	}
}
