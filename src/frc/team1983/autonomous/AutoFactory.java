package frc.team1983.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team1983.autonomous.routines.DoNothing;
import frc.team1983.autonomous.routines.InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint;
import frc.team1983.autonomous.routines.InFrontOfTrenchRunToRendezvousPointToTrenchRun;

public class AutoFactory
{
	public static Command getAuto(Auto auto)
	{
		switch (auto)
		{
			case IN_FRONT_OF_TRENCH_RUN_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN:
				return new InFrontOfTrenchRunToRendezvousPointToTrenchRun();
			case IN_FRONT_OF_OPPONENT_TRENCH_RUN_TO_OPPONENT_TRENCH_RUN_TO_RENDEZVOUS_POINT:
				return new InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint();
			default:
				return new DoNothing();
		}
	}
}
