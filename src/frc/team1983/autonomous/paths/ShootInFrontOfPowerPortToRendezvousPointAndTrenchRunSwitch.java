package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class ShootInFrontOfPowerPortToRendezvousPointAndTrenchRunSwitch extends FollowTrajectory
{
	public ShootInFrontOfPowerPortToRendezvousPointAndTrenchRunSwitch()
	{
		super(
			Constants.Pose.SHOOT_IN_FRONT_OF_POWER_PORT,
			Constants.Pose.RENDEZVOUS_POINT_AND_TRENCH_RUN_SWITCH
		);
	}
}
