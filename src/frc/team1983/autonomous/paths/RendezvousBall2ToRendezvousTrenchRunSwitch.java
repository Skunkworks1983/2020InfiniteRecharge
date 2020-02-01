package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall2ToRendezvousTrenchRunSwitch extends FollowTrajectory
{
	public RendezvousBall2ToRendezvousTrenchRunSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_2,
			Constants.Pose.RENDEZVOUS_TRENCH_RUN_SWITCH
		);
	}
}
