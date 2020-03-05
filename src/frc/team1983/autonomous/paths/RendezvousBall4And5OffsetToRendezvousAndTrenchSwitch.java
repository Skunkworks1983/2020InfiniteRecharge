package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall4And5OffsetToRendezvousAndTrenchSwitch extends FollowTrajectory
{
	public RendezvousBall4And5OffsetToRendezvousAndTrenchSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_BALL_4_AND_5_OFFSET,
			Constants.Pose.RENDEZVOUS_AND_TRENCH_SWITCH
		);
	}
}
