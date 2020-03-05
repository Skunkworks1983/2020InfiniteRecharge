package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class TrenchBall2ToTrenchBall4And5 extends FollowTrajectory
{
	public TrenchBall2ToTrenchBall4And5()
	{
		super(
			true,
			Constants.Pose.TRENCH_BALL_2,
			Constants.Pose.TRENCH_BALL_4_AND_5
		);
	}
}
