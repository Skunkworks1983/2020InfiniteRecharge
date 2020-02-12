package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousTrenchRunSwitchToTrenchRunBall4And5 extends FollowTrajectory
{
	public RendezvousTrenchRunSwitchToTrenchRunBall4And5()
	{
		super(
			true,
			Constants.Pose.RENDEZVOUS_TRENCH_RUN_SWITCH,
			Constants.Pose.TRENCH_RUN_BALL_1,
			Constants.Pose.TRENCH_RUN_BALL_4_AND_5
		);
	}
}
