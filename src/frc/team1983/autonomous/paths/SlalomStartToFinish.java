package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class SlalomStartToFinish extends FollowTrajectory
{
	public SlalomStartToFinish()
	{
		super(
			12,
			Constants.Pose.SLALOM_START_ZONE,
			Constants.Pose.SLALOM_D4_WAYPOINT_1,
			Constants.Pose.SLALOM_D6_WAYPOINT_1,
			Constants.Pose.SLALOM_D8_WAYPOINT_1,
			// Constants.Pose.SLALOM_D10_WAYPOINT_1,
			Constants.Pose.SLALOM_D10_WAYPOINT_2,
			// Constants.Pose.SLALOM_D10_WAYPOINT_3,
			Constants.Pose.SLALOM_D8_WAYPOINT_2,
			Constants.Pose.SLALOM_D6_WAYPOINT_2,
			Constants.Pose.SLALOM_D4_WAYPOINT_2,
			Constants.Pose.SLALOM_FINISH_ZONE
		);
	}
}
