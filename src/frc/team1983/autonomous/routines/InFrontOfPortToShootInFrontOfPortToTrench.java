package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfPortToShootInFrontOfPort;
import frc.team1983.autonomous.paths.InFrontOfPortToTrenchBall3;
import frc.team1983.autonomous.paths.ShootInFrontOfPortToInFrontOfPort;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfPortToShootInFrontOfPortToTrench extends SequentialCommandGroup
{
	public InFrontOfPortToShootInFrontOfPortToTrench()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_PORT),
			new ParallelCommandGroup(
				new InFrontOfPortToShootInFrontOfPort(),
				new DoNothing() // TODO: collect
			),
			new TargetAlignment(false).withTimeout(1.0),
			new ShootInFrontOfPortToInFrontOfPort(),
			new ParallelCommandGroup(
				new InFrontOfPortToTrenchBall3(),
				new DoNothing() // TODO: collect
			),
			new TargetAlignment(false).withTimeout(1.0)
		);
	}
}
