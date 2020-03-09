package frc.team1983.autonomous.paths;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.team1983.commands.FollowTrajectory;
import frc.team1983.constants.Constants;

public class TrenchBall3ToShootInFrontOfPort extends FollowTrajectory
{
	public TrenchBall3ToShootInFrontOfPort()
	{
		super(
			Constants.Pose.TRENCH_BALL_3,
			new Pose2d(Constants.Pose.SHOOT_IN_FRONT_OF_PORT.getTranslation(), new Rotation2d(Units.degreesToRadians(-135)))
		);
	}
}
