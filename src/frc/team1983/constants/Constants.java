package frc.team1983.constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;

public class Constants
{
    public static final double ROBOT_WIDTH = 34.0 / 12.0; // feet
    public static final double ROBOT_LENGTH = 37.0 /12.0; // feet

    public static final double TRACK_WIDTH = 24.75 / 12.0; // feet

    public static class Pose
    {
        public static final Pose2d START = new Pose2d(Units.feetToMeters(94.66 / 12.0), Units.feetToMeters(10 + ROBOT_LENGTH / 2.0), new Rotation2d(Units.degreesToRadians(180)));
        public static final Pose2d TRENCH_RUN_FIRST_BALL = new Pose2d(Units.feetToMeters(27.75 / 12.0), Units.feetToMeters(242.63 / 12.0 - ROBOT_LENGTH / 2.0), new Rotation2d(Units.degreesToRadians(180)));
        public static final Pose2d TRENCH_RUN_LAST_BALLS = new Pose2d(Units.feetToMeters(27.75 / 12.0), Units.feetToMeters(378.9 / 12.0 - ROBOT_LENGTH / 2.0), new Rotation2d(Units.degreesToRadians(180)));
    }
}
