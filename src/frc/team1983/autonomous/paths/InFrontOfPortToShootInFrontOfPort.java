package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPortToShootInFrontOfPort extends FollowTrajectory
{
	public InFrontOfPortToShootInFrontOfPort()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_PORT,
			Constants.Pose.SHOOT_IN_FRONT_OF_PORT
		);
	}
}
