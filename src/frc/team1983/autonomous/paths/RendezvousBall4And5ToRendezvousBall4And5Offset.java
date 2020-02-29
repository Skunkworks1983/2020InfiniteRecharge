package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall4And5ToRendezvousBall4And5Offset extends FollowTrajectory
{
	public RendezvousBall4And5ToRendezvousBall4And5Offset()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5,
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5_OFFSET
		);
	}
}
