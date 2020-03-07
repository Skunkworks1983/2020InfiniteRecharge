package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.InFrontOfPortToRendezvousBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall1And2And3AndRendezvousBall4And5SwitchToRendezvousBall1And2And3;
import frc.team1983.autonomous.paths.RendezvousBall1And2And3ToRendezvousBall1And2And3Offset;
import frc.team1983.autonomous.paths.RendezvousBall4And5OffsetToRendezvousBall1And2And3AndRendezvousBall4And5Switch;
import frc.team1983.autonomous.paths.RendezvousBall4And5ToRendezvousBall4And5Offset;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfPortToRendezvous extends SequentialCommandGroup
{
	public InFrontOfPortToRendezvous()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_PORT),
			new ParallelCommandGroup(
				new InFrontOfPortToRendezvousBall4And5(),
				new DoNothing() // TODO: collect
			),
			new RendezvousBall4And5ToRendezvousBall4And5Offset(),
			new TargetAlignment(false).withTimeout(1.0),
			new RendezvousBall4And5OffsetToRendezvousBall1And2And3AndRendezvousBall4And5Switch(),
			new ParallelCommandGroup(
				new RendezvousBall1And2And3AndRendezvousBall4And5SwitchToRendezvousBall1And2And3(),
				new DoNothing() // TODO: collect
			),
			new RendezvousBall1And2And3ToRendezvousBall1And2And3Offset(),
			new TargetAlignment(true).withTimeout(1.0)
		);
	}
}
