package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPortToRendezvousBall4And5 extends FollowTrajectory
{
	public InFrontOfPortToRendezvousBall4And5()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_PORT,
			Constants.Pose.RENDEZVOUS_BALL_4_AND_5
		);
	}
}
