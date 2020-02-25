package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class OpponentTrenchRunBall4And5ToOpponentTrenchRunAndRendezvousPointSwitch extends FollowTrajectory
{
	public OpponentTrenchRunBall4And5ToOpponentTrenchRunAndRendezvousPointSwitch()
	{
		super(
			Constants.Pose.OPPONENT_TRENCH_RUN_BALL_4_AND_5,
			Constants.Pose.OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_SWITCH
		);
	}
}
