package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfPowerPortToRendezvousBall1And2And3 extends FollowTrajectory
{
	public InFrontOfPowerPortToRendezvousBall1And2And3()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_POWER_PORT,
			Constants.Pose.RENDEZVOUS_POINT_BALL_1_AND_2_AND_3
		);
	}
}
