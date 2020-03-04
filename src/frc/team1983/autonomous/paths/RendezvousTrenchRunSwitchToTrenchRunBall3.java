package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousTrenchRunSwitchToTrenchRunBall3 extends FollowTrajectory
{
	public RendezvousTrenchRunSwitchToTrenchRunBall3()
	{
		super(
			true,
			Constants.Pose.RENDEZVOUS_POINT_AND_TRENCH_RUN_SWITCH,
			Constants.Pose.TRENCH_RUN_BALL_1,
			Constants.Pose.TRENCH_RUN_BALL_3
		);
	}
}
