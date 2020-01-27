package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall1And2ToRendezvousTrenchRunSwitch extends FollowTrajectory
{
	public RendezvousBall1And2ToRendezvousTrenchRunSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_1_AND_2,
			Constants.Pose.RENDEZVOUS_TRENCH_RUN_SWITCH
		);
	}
}
