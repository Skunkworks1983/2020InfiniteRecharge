package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class StartInFrontOfTrenchRunToRendezvousBall2 extends FollowTrajectory
{
	public StartInFrontOfTrenchRunToRendezvousBall2()
	{
		super(
			true,
			Constants.Pose.START_IN_FRONT_OF_TRENCH_RUN,
			Constants.Pose.RENDEZVOUS_POINT_BALL_2
		);
	}
}
