package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.CompliantShotToRendezvousBall1And2And3;
import frc.team1983.autonomous.paths.OpponentTrenchRunBall4And5ToCompliantShot;
import frc.team1983.autonomous.paths.StartInFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint extends SequentialCommandGroup
{
	public InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint()
	{
		addCommands(
			new SetPose(Constants.Pose.START_IN_FRONT_OF_OPPONENT_TRENCH_RUN),
			new StartInFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5(),
			new OpponentTrenchRunBall4And5ToCompliantShot(),
			new TargetAlignment(false).withTimeout(1.0),
			new CompliantShotToRendezvousBall1And2And3(),
			new TargetAlignment(true).withTimeout(1.0)
		);
	}
}
