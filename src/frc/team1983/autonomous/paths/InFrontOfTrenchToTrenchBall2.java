package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchToTrenchBall2 extends FollowTrajectory
{
	public InFrontOfTrenchToTrenchBall2()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_TRENCH,
			Constants.Pose.TRENCH_BALL_2
		);
	}
}
