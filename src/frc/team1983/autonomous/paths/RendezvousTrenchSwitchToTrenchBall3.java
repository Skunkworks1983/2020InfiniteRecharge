package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousTrenchSwitchToTrenchBall3 extends FollowTrajectory
{
	public RendezvousTrenchSwitchToTrenchBall3()
	{
		super(
			true,
			Constants.Pose.RENDEZVOUS_AND_TRENCH_SWITCH,
			Constants.Pose.TRENCH_BALL_1,
			Constants.Pose.TRENCH_BALL_3
		);
	}
}
