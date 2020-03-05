package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class TrenchBall4And5ToTrenchBall3 extends FollowTrajectory
{
	public TrenchBall4And5ToTrenchBall3()
	{
		super(
			Constants.Pose.TRENCH_BALL_4_AND_5,
			Constants.Pose.TRENCH_BALL_3
		);
	}
}
