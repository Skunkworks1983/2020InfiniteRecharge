package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class StartInFrontOfTrenchRunToRendezvousBall5 extends FollowTrajectory
{
	public StartInFrontOfTrenchRunToRendezvousBall5()
	{
		super(
			true,
			Constants.Pose.START_IN_FRONT_OF_TRENCH_RUN,
			Constants.Pose.RENDEZVOUS_POINT_BALL_5
		);
	}
}
