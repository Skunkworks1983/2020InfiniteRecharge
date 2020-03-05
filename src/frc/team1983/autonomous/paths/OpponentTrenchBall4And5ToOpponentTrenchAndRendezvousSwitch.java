package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class OpponentTrenchBall4And5ToOpponentTrenchAndRendezvousSwitch extends FollowTrajectory
{
	public OpponentTrenchBall4And5ToOpponentTrenchAndRendezvousSwitch()
	{
		super(
			Constants.Pose.OPPONENT_TRENCH_BALL_4_AND_5,
			Constants.Pose.OPPONENT_TRENCH_AND_RENDEZVOUS_SWITCH
		);
	}
}
