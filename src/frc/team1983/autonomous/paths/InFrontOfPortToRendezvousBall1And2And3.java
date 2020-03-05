package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPortToRendezvousBall1And2And3 extends FollowTrajectory
{
	public InFrontOfPortToRendezvousBall1And2And3()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_PORT,
			Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3
		);
	}
}
