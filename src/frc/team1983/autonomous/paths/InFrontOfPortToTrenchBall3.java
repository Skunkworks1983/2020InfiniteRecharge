package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPortToTrenchBall3 extends FollowTrajectory
{
	public InFrontOfPortToTrenchBall3()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_PORT,
			Constants.Pose.TRENCH_BALL_1,
			Constants.Pose.TRENCH_BALL_3
		);
	}
}
