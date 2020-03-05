package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfPortToRendezvousBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall4And5OffsetToRendezvousAndTrenchSwitch;
import frc.team1983.autonomous.paths.RendezvousBall4And5ToRendezvousBall4And5Offset;
import frc.team1983.autonomous.paths.RendezvousTrenchSwitchToTrenchBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfPortToRendezvousToTrench extends SequentialCommandGroup
{
	public InFrontOfPortToRendezvousToTrench()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_PORT),
			new ParallelCommandGroup(
				new InFrontOfPortToRendezvousBall4And5(),
				new DoNothing() // TODO: collect
			),
			new RendezvousBall4And5ToRendezvousBall4And5Offset(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5OffsetToRendezvousAndTrenchSwitch(),
			new ParallelCommandGroup(
				new RendezvousTrenchSwitchToTrenchBall4And5(),
				new DoNothing() // TODO: collect
			)
		);
	}
}
