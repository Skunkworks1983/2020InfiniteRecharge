package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class CompliantShotToRendezvousBall1And2And3 extends FollowTrajectory
{
	public CompliantShotToRendezvousBall1And2And3()
	{
		super(
			true,
			Constants.Pose.COMPLAINT_SHOT,
			Constants.Pose.RENDEZVOUS_POINT_BALL_1_AND_2_AND_3
		);
	}
}
