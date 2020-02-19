package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team1983.autonomous.paths.*;
import frc.team1983.commands.TargetAlignment;

public class InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint extends SequentialCommandGroup
{
	public InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint()
	{
		addCommands(
			new StartInFrontOfOpponentTrenchRunToOpponentTrenchRunBall4And5(),
			new WaitCommand(1.0),
			new OpponentTrenchRunBall4And5ToCompliantShot(),
			new WaitCommand(1.0),
//			new TargetAlignment(false).withTimeout(1.0),
			new CompliantShotToRendezvousBall1And2And3(),
			new WaitCommand(1.0)
//			new TargetAlignment(true).withTimeout(1.0)
		);
	}
}
