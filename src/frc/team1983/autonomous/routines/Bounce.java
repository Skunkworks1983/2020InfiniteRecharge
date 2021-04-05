package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.BounceA3ToA6;
import frc.team1983.autonomous.paths.BounceA6ToA9;
import frc.team1983.autonomous.paths.BounceA9ToFinish;
import frc.team1983.autonomous.paths.BounceStartToA3;
import frc.team1983.commands.SetPose;
import frc.team1983.constants.Constants;

public class Bounce extends SequentialCommandGroup
{
	public Bounce()
	{
		addCommands(
			new SetPose(Constants.Pose.BOUNCE_START_ZONE),
			new BounceStartToA3().withTimeout(1.35),
			new BounceA3ToA6().withTimeout(2.8),
			new BounceA6ToA9().withTimeout(3.2),
			new BounceA9ToFinish()
		);
	}
}
