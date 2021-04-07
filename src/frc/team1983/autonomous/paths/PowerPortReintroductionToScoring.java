package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class PowerPortReintroductionToScoring extends FollowTrajectory
{
	public PowerPortReintroductionToScoring()
	{
		super(
			14,
			10,
			Constants.Pose.POWER_PORT_REINTRODUCTION_ZONE,
			Constants.Pose.POWER_PORT_SCORING_ZONE
		);
	}
}
