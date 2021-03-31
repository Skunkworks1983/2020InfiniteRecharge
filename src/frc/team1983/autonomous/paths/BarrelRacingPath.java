package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BarrelRacingPath extends FollowTrajectory
{
	public BarrelRacingPath()
	{
		super(
			1,
			Constants.Pose.BARREL_RACING_START_ZONE,
			Constants.Pose.BARREL_RACING_FINISH_ZONE
		);
	}
}
