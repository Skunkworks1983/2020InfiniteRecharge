package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfPowerPortToRendezvousBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall4And5ToRendezvousPointAndTrenchRunSwitch;
import frc.team1983.autonomous.paths.RendezvousTrenchRunSwitchToTrenchRunBall4And5;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfPowerPortToRendezvousPointToTrenchRun extends SequentialCommandGroup
{
	public InFrontOfPowerPortToRendezvousPointToTrenchRun()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_POWER_PORT),
			new InFrontOfPowerPortToRendezvousBall4And5(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5ToRendezvousPointAndTrenchRunSwitch(),
			new RendezvousTrenchRunSwitchToTrenchRunBall4And5()
		);
	}
}
