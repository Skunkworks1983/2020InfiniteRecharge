package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class GalacticSearchPathARedStartToFinish extends FollowTrajectory
{
	public GalacticSearchPathARedStartToFinish()
	{
		super(
			5,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_RED_START,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_C3,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_D5,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_A6,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_RED_FINISH
		);
	}
}
