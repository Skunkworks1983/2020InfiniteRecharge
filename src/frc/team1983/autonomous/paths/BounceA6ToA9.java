package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BounceA6ToA9 extends FollowTrajectory
{
	public BounceA6ToA9()
	{
		super(
			10,
			Constants.Pose.BOUNCE_A6,
			Constants.Pose.BOUNCE_E7,
			Constants.Pose.BOUNCE_A9
		);
	}
}
