package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchRunToTrenchRunBall2 extends FollowTrajectory
{
	public InFrontOfTrenchRunToTrenchRunBall2()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_TRENCH_RUN,
			Constants.Pose.TRENCH_RUN_BALL_2
		);
	}
}
