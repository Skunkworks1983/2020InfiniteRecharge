package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfTrenchToTrenchBall2;
import frc.team1983.autonomous.paths.TrenchBall2ToTrenchBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchToTrench extends SequentialCommandGroup
{
	public InFrontOfTrenchToTrench()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_TRENCH),
			new ParallelCommandGroup(
				new InFrontOfTrenchToTrenchBall2(),
				new DoNothing() // TODO: collect
			),
			new TargetAlignment(false).withTimeout(1.0),
			new TrenchBall2ToTrenchBall4And5()
		);
	}
}
