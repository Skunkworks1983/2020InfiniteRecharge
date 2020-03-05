package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class ShootInFrontOfPortToRendezvousAndTrenchSwitch extends FollowTrajectory
{
	public ShootInFrontOfPortToRendezvousAndTrenchSwitch()
	{
		super(
			Constants.Pose.SHOOT_IN_FRONT_OF_PORT,
			Constants.Pose.RENDEZVOUS_AND_TRENCH_SWITCH
		);
	}
}
