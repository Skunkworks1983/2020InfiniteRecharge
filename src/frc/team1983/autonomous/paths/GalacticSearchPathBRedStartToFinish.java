package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class GalacticSearchPathBRedStartToFinish extends FollowTrajectory
{
	public GalacticSearchPathBRedStartToFinish()
	{
		super(
			true,
			14,
			12,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_RED_START,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_B3,
			// Constants.Pose.GALACTIC_SEARCH_PATH_B_D5,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_B7,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_RED_FINISH
		);
	}
}
