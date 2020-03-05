package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall1And2And3ToRendezvousBall1And2And3AndRendezvousBall4And5Switch extends FollowTrajectory
{
	public RendezvousBall1And2And3ToRendezvousBall1And2And3AndRendezvousBall4And5Switch()
	{
		super(
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3,
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_SWITCH
		);
	}
}
