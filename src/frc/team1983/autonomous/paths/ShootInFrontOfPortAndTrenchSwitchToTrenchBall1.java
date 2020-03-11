package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class ShootInFrontOfPortAndTrenchSwitchToTrenchBall1 extends FollowTrajectory
{
	public ShootInFrontOfPortAndTrenchSwitchToTrenchBall1()
	{
		super(
			true,
			5.0,
			Constants.Pose.SHOOT_IN_FRONT_OF_PORT_TOWARD_TRENCH_BALL_1,
			Constants.Pose.TRENCH_BALL_1_FROM_SHOOT_IN_FRONT_OF_PORT
		);
	}
}
