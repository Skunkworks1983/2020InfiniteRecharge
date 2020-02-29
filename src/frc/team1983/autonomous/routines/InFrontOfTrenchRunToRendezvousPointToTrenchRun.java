package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfTrenchRunToRendezvousBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall4And5OffsetToRendezvousPointAndTrenchRunSwitch;
import frc.team1983.autonomous.paths.RendezvousBall4And5ToRendezvousBall4And5Offset;
import frc.team1983.autonomous.paths.RendezvousTrenchRunSwitchToTrenchRunBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchRunToRendezvousPointToTrenchRun extends SequentialCommandGroup
{
	public InFrontOfTrenchRunToRendezvousPointToTrenchRun()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_TRENCH_RUN),
			new InFrontOfTrenchRunToRendezvousBall4And5(),
			new RendezvousBall4And5ToRendezvousBall4And5Offset(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5OffsetToRendezvousPointAndTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchToTrenchRunBall4And5()
		);
	}
}
