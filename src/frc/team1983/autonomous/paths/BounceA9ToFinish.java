package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BounceA9ToFinish extends FollowTrajectory
{
	public BounceA9ToFinish()
	{
		super(
			true,
			14,
			Constants.Pose.BOUNCE_A9,
			Constants.Pose.BOUNCE_FINISH_ZONE
		);
	}
}
