package frc.team1983.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team1983.autonomous.routines.DoNothing;
import frc.team1983.autonomous.routines.InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint;
import frc.team1983.autonomous.routines.InFrontOfPowerPortToRendezvousPoint;
import frc.team1983.autonomous.routines.InFrontOfPowerPortToRendezvousPointToTrenchRun;
import frc.team1983.autonomous.routines.InFrontOfTrenchRunToRendezvousPointToTrenchRun;
import frc.team1983.autonomous.routines.InFrontOfTrenchRunToTrenchRun;

import java.util.function.Supplier;

public enum Auto
{
	DO_NOTHING(DoNothing::new),
	IN_FRONT_OF_OPPONENT_TRENCH_RUN_TO_OPPONENT_TRENCH_RUN_TO_RENDEZVOUS_POINT(InFrontOfOpponentTrenchRunToOpponentTrenchRunToRendezvousPoint::new),
	IN_FRONT_OF_POWER_PORT_TO_RENDEZVOUS_POINT(InFrontOfPowerPortToRendezvousPoint::new),
	IN_FRONT_OF_POWER_PORT_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN(InFrontOfPowerPortToRendezvousPointToTrenchRun::new),
	IN_FRONT_OF_TRENCH_RUN_TO_RENDEZVOUS_POINT_TO_TRENCH_RUN(InFrontOfTrenchRunToRendezvousPointToTrenchRun::new),
	IN_FRONT_OF_TRENCH_RUN_TO_TRENCH_RUN(InFrontOfTrenchRunToTrenchRun::new);

	private Supplier<Command> auto;

	Auto(Supplier<Command> auto)
	{
		this.auto = auto;
	}

	public Command getAuto()
	{
		return auto.get();
	}
}
