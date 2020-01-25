package frc.team1983.constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import org.junit.Assert;
import org.junit.Test;

public class UT_Pose
{
    @Test
    public void printPoses()
    {
        System.out.println(Constants.Pose.START);
        System.out.println(Constants.Pose.TRENCH_RUN_BALL_1);
        System.out.println(Constants.Pose.TRENCH_RUN_BALL_4_AND_5);
        System.out.println(Constants.Pose.RENDEZVOUS_POINT_BALL_1 + ", " + Constants.Pose.RENDEZVOUS_POINT_BALL_1.getRotation().getCos() + ", " + Constants.Pose.RENDEZVOUS_POINT_BALL_1.getRotation().getSin());
        System.out.println(Constants.Pose.RENDEZVOUS_POINT_BALL_2 + ", " + Constants.Pose.RENDEZVOUS_POINT_BALL_2.getRotation().getCos() + ", " + Constants.Pose.RENDEZVOUS_POINT_BALL_2.getRotation().getSin());
    }

    @Test
    public void constantPosesStayConstant()
    {
        Pose2d pose = new Pose2d();
        pose.plus(new Transform2d(new Translation2d(19.0, 83.0), new Rotation2d(90)));
        Assert.assertTrue(pose.equals(new Pose2d()));
    }
}
