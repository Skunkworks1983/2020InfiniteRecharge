package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class RendezvousBall4And5OffsetToRendezvousPointAndTrenchRunSwitch extends FollowTrajectory
{
	public RendezvousBall4And5OffsetToRendezvousPointAndTrenchRunSwitch()
	{
		super(
			Constants.Pose.RENDEZVOUS_POINT_BALL_4_AND_5_OFFSET,
			Constants.Pose.RENDEZVOUS_POINT_AND_TRENCH_RUN_SWITCH
		);
	}
}
