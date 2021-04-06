package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class GalacticSearchPathARedA6ToFinish extends FollowTrajectory
{
	public GalacticSearchPathARedA6ToFinish()
	{
		super(
			14,
			12,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_A6_FINISH_WAYPOINT,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_RED_FINISH
		);
	}
}
