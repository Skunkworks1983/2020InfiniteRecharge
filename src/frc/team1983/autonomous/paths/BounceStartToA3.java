package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BounceStartToA3 extends FollowTrajectory
{
	public BounceStartToA3()
	{
		super(
			14,
			Constants.Pose.BOUNCE_START_ZONE,
			Constants.Pose.BOUNCE_A3
		);
	}
}
