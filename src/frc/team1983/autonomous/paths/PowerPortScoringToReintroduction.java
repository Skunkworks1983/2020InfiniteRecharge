package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class PowerPortScoringToReintroduction extends FollowTrajectory
{
	public PowerPortScoringToReintroduction()
	{
		super(
			true,
			14,
			10,
			Constants.Pose.POWER_PORT_SCORING_ZONE,
			Constants.Pose.POWER_PORT_REINTRODUCTION_ZONE
		);
	}
}
