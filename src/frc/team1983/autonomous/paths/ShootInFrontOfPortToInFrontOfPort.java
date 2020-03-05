package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class ShootInFrontOfPortToInFrontOfPort extends FollowTrajectory
{
	public ShootInFrontOfPortToInFrontOfPort()
	{
		super(
			Constants.Pose.SHOOT_IN_FRONT_OF_PORT,
			Constants.Pose.IN_FRONT_OF_PORT
		);
	}
}
