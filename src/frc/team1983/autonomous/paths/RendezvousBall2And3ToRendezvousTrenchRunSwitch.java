package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall2And3ToRendezvousTrenchRunSwitch extends FollowTrajectory
{
	public RendezvousBall2And3ToRendezvousTrenchRunSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_2_AND_3,
			Constants.Pose.RENDEZVOUS_TRENCH_RUN_SWITCH
		);
	}
}
