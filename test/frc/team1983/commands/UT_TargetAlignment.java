package frc.team1983.commands;

import frc.team1983.constants.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UT_TargetAlignment
{
	@Test
	public void targetAngleFromInFrontOfTargetIs180()
	{
		TargetAlignment targetAlignment = new TargetAlignment(Constants.Pose.SHOOT_IN_FRONT_OF_PORT);
		Assert.assertEquals(180.0, targetAlignment.getTargetAngle(), 0.0);
	}

	@Test
	public void targetAngleIsGreaterThan180WhenPoseIsLeftOfTarget()
	{
		TargetAlignment targetAlignment = new TargetAlignment(Constants.Pose.TRENCH_BALL_3);
		Assert.assertTrue(targetAlignment.getTargetAngle() > 180.0);
	}

	@Test
	public void targetAngleIsLessThan180WhenPoseIsRightOfTarget()
	{
		TargetAlignment targetAlignment = new TargetAlignment(Constants.Pose.OPPONENT_TRENCH_BALL_4_AND_5);
		Assert.assertTrue(targetAlignment.getTargetAngle() < 180.0);
	}
}
