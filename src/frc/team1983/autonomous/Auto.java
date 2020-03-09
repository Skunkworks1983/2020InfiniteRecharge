package frc.team1983.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team1983.autonomous.routines.DoNothing;
import frc.team1983.autonomous.routines.InFrontOfOpponentTrenchToOpponentTrenchToRendezvous;
import frc.team1983.autonomous.routines.InFrontOfPortToRendezvous;
import frc.team1983.autonomous.routines.InFrontOfPortToRendezvousToTrench;
import frc.team1983.autonomous.routines.InFrontOfPortToShootInFrontOfPortToTrench;
import frc.team1983.autonomous.routines.InFrontOfPortToShootInFrontOfPortToTrenchToShootInFrontOfPort;
import frc.team1983.autonomous.routines.InFrontOfTrenchToRendezvousToTrench;
import frc.team1983.autonomous.routines.InFrontOfTrenchToTrench;

import java.util.function.Supplier;

public enum Auto
{
	DO_NOTHING(DoNothing::new),
	DRIVE_FORWARD(() -> new DriveStraight(0.5, 0.25).withTimeout(1.5)),
	DRIVE_BACKWARD(() -> new DriveStraight(-0.5, 0.25).withTimeout(1.5)),
	IN_FRONT_OF_OPPONENT_TRENCH_TO_OPPONENT_TRENCH_TO_RENDEZVOUS(InFrontOfOpponentTrenchToOpponentTrenchToRendezvous::new),
	IN_FRONT_OF_PORT_TO_RENDEZVOUS(InFrontOfPortToRendezvous::new),
	IN_FRONT_OF_PORT_TO_RENDEZVOUS_TO_TRENCH(InFrontOfPortToRendezvousToTrench::new),
	IN_FRONT_OF_TRENCH_TO_RENDEZVOUS_TO_TRENCH(InFrontOfTrenchToRendezvousToTrench::new),
	IN_FRONT_OF_TRENCH_TO_TRENCH(InFrontOfTrenchToTrench::new),
	IN_FRONT_OF_PORT_TO_SHOOT_IN_FRONT_OF_PORT_TO_TRENCH(InFrontOfPortToShootInFrontOfPortToTrench::new),
	IN_FRONT_OF_PORT_TO_SHOOT_IN_FRONT_OF_PORT_TO_TRENCH_TO_SHOOT_IN_FRONT_OF_TRENCH(InFrontOfPortToShootInFrontOfPortToTrenchToShootInFrontOfPort::new);

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
