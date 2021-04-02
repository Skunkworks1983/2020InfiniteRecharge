package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class GalacticSearchPathABlueStartToFinish extends FollowTrajectory
{
	public GalacticSearchPathABlueStartToFinish()
	{
		super(
			5,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_BLUE_START,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_E6,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_B7,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_C9,
			Constants.Pose.GALACTIC_SEARCH_PATH_A_BLUE_FINISH
		);
	}
}
