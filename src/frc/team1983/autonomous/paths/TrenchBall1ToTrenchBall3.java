package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class TrenchBall1ToTrenchBall3 extends FollowTrajectory
{
	public TrenchBall1ToTrenchBall3()
	{
		super(
			true,
			5.0,
			Constants.Pose.TRENCH_BALL_1,
			Constants.Pose.TRENCH_BALL_3
		);
	}
}
