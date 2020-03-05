package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall1And2And3ToRendezvousBall1And2And3Offset extends FollowTrajectory
{
	public RendezvousBall1And2And3ToRendezvousBall1And2And3Offset()
	{
		super(
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3,
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3_OFFSET
		);
	}
}
