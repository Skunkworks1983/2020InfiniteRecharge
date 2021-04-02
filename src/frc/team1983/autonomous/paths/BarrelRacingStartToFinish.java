package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class BarrelRacingStartToFinish extends FollowTrajectory
{
	public BarrelRacingStartToFinish()
	{
		super(
			10,
			Constants.Pose.BARREL_RACING_START_ZONE,
			// Constants.Pose.BARREL_RACING_D5_WAYPOINT_1,
			Constants.Pose.BARREL_RACING_D5_WAYPOINT_2,
			// Constants.Pose.BARREL_RACING_D5_WAYPOINT_3,
			Constants.Pose.BARREL_RACING_D5_WAYPOINT_4,
			Constants.Pose.BARREL_RACING_D5_B8_WAYPOINT,
			// Constants.Pose.BARREL_RACING_B8_WAYPOINT_1,
			Constants.Pose.BARREL_RACING_B8_WAYPOINT_2,
			// Constants.Pose.BARREL_RACING_B8_WAYPOINT_3,
			Constants.Pose.BARREL_RACING_B8_WAYPOINT_4,
			// Constants.Pose.BARREL_RACING_B8_D10_WAYPOINT,
			// Constants.Pose.BARREL_RACING_D10_WAYPOINT_1,
			Constants.Pose.BARREL_RACING_D10_WAYPOINT_2,
			// Constants.Pose.BARREL_RACING_D10_WAYPOINT_3,
			Constants.Pose.BARREL_RACING_D10_FINISH_ZONE_WAYPOINT,
			Constants.Pose.BARREL_RACING_FINISH_ZONE
		);
	}
}
