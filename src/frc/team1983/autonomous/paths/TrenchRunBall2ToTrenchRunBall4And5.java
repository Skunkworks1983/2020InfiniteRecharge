package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class TrenchRunBall2ToTrenchRunBall4And5 extends FollowTrajectory
{
	public TrenchRunBall2ToTrenchRunBall4And5()
	{
		super(
			true,
			Constants.Pose.TRENCH_RUN_BALL_2,
			Constants.Pose.TRENCH_RUN_BALL_4_AND_5
		);
	}
}
