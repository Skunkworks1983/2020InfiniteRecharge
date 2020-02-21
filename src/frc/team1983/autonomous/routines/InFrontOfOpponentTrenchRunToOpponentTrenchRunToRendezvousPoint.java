package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.CompliantShotToRendezvousBall1And2And3;
import frc.team1983.autonomous.paths.OpponentTrenchRunBall4And5ToCompliantShot;
import frc.team1983.autonomous.paths.StartInFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5;
import frc.team1983.commands.TargetAlignment;

public class InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint extends SequentialCommandGroup
{
	public InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint()
	{
		addCommands(
			new StartInFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5(),
			new OpponentTrenchRunBall4And5ToCompliantShot(),
			new TargetAlignment(false).withTimeout(1.0),
			new CompliantShotToRendezvousBall1And2And3(),
			new TargetAlignment(true).withTimeout(1.0)
		);
	}
}
