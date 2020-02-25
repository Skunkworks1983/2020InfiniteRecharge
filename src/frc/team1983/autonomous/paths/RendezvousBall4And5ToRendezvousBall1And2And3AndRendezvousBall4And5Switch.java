package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall4And5ToRendezvousBall1And2And3AndRendezvousBall4And5Switch extends FollowTrajectory
{
	public RendezvousBall4And5ToRendezvousBall1And2And3AndRendezvousBall4And5Switch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5,
			Constants.Pose.RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_SWITCH
		);
	}
}
