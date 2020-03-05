package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class OpponentTrenchAndRendezvousSwitchToRendezvousBall1And2And3 extends FollowTrajectory
{
	public OpponentTrenchAndRendezvousSwitchToRendezvousBall1And2And3()
	{
		super(
			true,
			Constants.Pose.OPPONENT_TRENCH_AND_RENDEZVOUS_SWITCH,
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3
		);
	}
}
