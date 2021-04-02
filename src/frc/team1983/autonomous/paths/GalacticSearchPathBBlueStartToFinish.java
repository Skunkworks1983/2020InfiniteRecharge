package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class GalacticSearchPathBBlueStartToFinish extends FollowTrajectory
{
	public GalacticSearchPathBBlueStartToFinish()
	{
		super(
			5,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_BLUE_START,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_D6,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_B8,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_D10,
			Constants.Pose.GALACTIC_SEARCH_PATH_B_BLUE_FINISH
		);
	}
}
