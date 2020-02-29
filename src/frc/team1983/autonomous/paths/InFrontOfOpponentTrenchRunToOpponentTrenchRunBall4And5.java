package frc.team1983.autonomous.paths;

import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class InFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5 extends FollowTrajectory
{
	public InFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5()
	{
		super(
			true,
			Constants.Pose.IN_FRONT_OF_OPPONENT_TRENCH_RUN,
			Constants.Pose.OPPONENT_TRENCH_RUN_BALL_4_AND_5
		);
	}
}