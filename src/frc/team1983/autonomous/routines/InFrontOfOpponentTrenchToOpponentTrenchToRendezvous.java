package frc.team1983.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team1983.autonomous.paths.OpponentTrenchAndRendezvousSwitchToRendezvousBall1And2And3;
import frc.team1983.autonomous.paths.OpponentTrenchBall4And5ToOpponentTrenchAndRendezvousSwitch;
import frc.team1983.autonomous.paths.InFrontOfOpponentTrenchToOpponentTrenchBall4And5;
import frc.team1983.autonomous.paths.RendezvousBall1And2And3ToRendezvousBall1And2And3Offset;
import frc.team1983.commands.SetPose;
import frc.team1983.commands.TargetAlignment;
import frc.team1983.constants.Constants;

public class InFrontOfOpponentTrenchToOpponentTrenchToRendezvous extends SequentialCommandGroup
{
	public InFrontOfOpponentTrenchToOpponentTrenchToRendezvous()
	{
		addCommands(
			new SetPose(Constants.Pose.IN_FRONT_OF_OPPONENT_TRENCH),
			new InFrontOfOpponentTrenchToOpponentTrenchBall4And5(),
			new OpponentTrenchBall4And5ToOpponentTrenchAndRendezvousSwitch(),
			new TargetAlignment(Constants.Pose.OPPONENT_TRENCH_AND_RENDEZVOUS_SWITCH).withTimeout(1.0),
			new OpponentTrenchAndRendezvousSwitchToRendezvousBall1And2And3(),
			new RendezvousBall1And2And3ToRendezvousBall1And2And3Offset(),
			new TargetAlignment(Constants.Pose.RENDEZVOUS_BALL_1_AND_2_AND_3_OFFSET).withTimeout(1.0)
		);
	}
}
