package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPortToTrenchBall3 extends FollowTrajectory
{
	public InFrontOfPortToTrenchBall3()
	{
		super(
			true,
			5.0,
			Constants.Pose.IN_FRONT_OF_PORT,
			Constants.Pose.IN_FRONT_OF_PORT_AND_TRENCH_SWITCH,
			Constants.Pose.TRENCH_BALL_3
		);
	}
}
