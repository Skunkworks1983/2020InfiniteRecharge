package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall4And5ToRendezvousTrenchRunSwitch extends FollowTrajectory
{
	public RendezvousBall4And5ToRendezvousTrenchRunSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5,
			Constants.Pose.RENDEZVOUS_TRENCH_RUN_SWITCH
		);
	}
}
