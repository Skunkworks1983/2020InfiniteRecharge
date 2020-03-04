package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfTrenchRunToTrenchRunBall2;
import frc.team1983.autonomous.paths.TrenchRunBall2ToTrenchRunBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfTrenchRunToTrenchRun extends SequentialCommandGroup
{
	public InFrontOfTrenchRunToTrenchRun()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_TRENCH_RUN),
			new ParallelCommandGroup(
				new InFrontOfTrenchRunToTrenchRunBall2(),
				new DoNothing() // TODO: collect
			),
			new TargetAlignment(false).withTimeout(1.0),
			new TrenchRunBall2ToTrenchRunBall4And5()
		);
	}
}
