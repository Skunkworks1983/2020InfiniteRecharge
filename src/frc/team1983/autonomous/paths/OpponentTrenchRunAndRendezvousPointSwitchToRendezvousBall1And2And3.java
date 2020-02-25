package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class OpponentTrenchRunAndRendezvousPointSwitchToRendezvousBall1And2And3 extends FollowTrajectory
{
	public OpponentTrenchRunAndRendezvousPointSwitchToRendezvousBall1And2And3()
	{
		super(
			true,
			Constants.Pose.OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_SWITCH,
			Constants.Pose.RENDEZVOUS_POINT_BALL_1_AND_2_AND_3
		);
	}
}
