package frc.team1983.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team1983.autonomous.routines.DoNothing;
import frc.team1983.autonomous.routines.RendezvousToTrench;

public class AutoFactory
{
	public static Command getAuto(Auto auto)
	{
		switch (auto)
		{
			case RENDEZVOUS_TO_TRENCH:
				return new RendezvousToTrench();
			default:
				return new DoNothing();
		}
	}
}
