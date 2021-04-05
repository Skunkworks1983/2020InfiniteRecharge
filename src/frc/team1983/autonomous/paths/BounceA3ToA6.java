package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BounceA3ToA6 extends FollowTrajectory
{
	public BounceA3ToA6()
	{
		super(
			true,
			12,
			Constants.Pose.BOUNCE_A3,
			Constants.Pose.BOUNCE_A3_E5_WAYPOINT,
			Constants.Pose.BOUNCE_E5,
			Constants.Pose.BOUNCE_A6
		);
	}
}
