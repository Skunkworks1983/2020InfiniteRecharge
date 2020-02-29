package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.OpponentTrenchRunAndRendezvousPointSwitchToRendezvousBall1And2And3;
import frc.team1983.autonomous.paths.OpponentTrenchRunBall4And5ToOpponentTrenchRunAndRendezvousPointSwitch;
import frc.team1983.autonomous.paths.InFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall1And2And3ToRendezvousBall1And2And3Offset;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint extends SequentialCommandGroup
{
	public InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_OPPONENT_TRENCH_RUN),
			new InFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5(),
			new OpponentTrenchRunBall4And5ToOpponentTrenchRunAndRendezvousPointSwitch(),
			new TargetAlignment(true).withTimeout(1.0),
			new OpponentTrenchRunAndRendezvousPointSwitchToRendezvousBall1And2And3(),
			new RendezvousBall1And2And3ToRendezvousBall1And2And3Offset(),
			new TargetAlignment(true).withTimeout(1.0)
		);
	}
}
