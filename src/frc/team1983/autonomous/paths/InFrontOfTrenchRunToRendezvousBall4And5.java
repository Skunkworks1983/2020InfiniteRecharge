package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchRunToRendezvousBall4And5 extends FollowTrajectory
{
	public InFrontOfTrenchRunToRendezvousBall4And5()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_TRENCH_RUN,
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5
		);
	}
}
