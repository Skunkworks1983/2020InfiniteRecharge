package frc.team1983.constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;

public class Constants
{
    public static final double ROBOT_WIDTH = 34.0 / 12.0; // feet
    public static final double ROBOT_LENGTH = 37.0 /12.0; // feet
    public static final double TRACK_WIDTH = 24.75 / 12.0; // feet

    /**
     * All poses are in meters
     *
     * IN_FRONT_OF is a starting pose on the initiation line
     * SWITCH is an intermediate pose between two other poses, the poses are separated by AND
     * OFFSET is an x or y offset from another pose
     * TRENCH_RUN ball 1 is closest to the initiation line, ball 4 and 5 are furthest
     * RENDEZVOUS_POINT ball 1 is closest to the center of the rendezvous point and increase clockwise
     * OPPONENT_TRENCH_RUN ball 1 is closest to the opponent initiation line, ball 4 and 5 are furthest
     */
    public static class Pose
    {
        // Front bumper touching initiation line
        public static final Pose2d IN_FRONT_OF_TRENCH_RUN = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d IN_FRONT_OF_POWER_PORT = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-94.66 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d IN_FRONT_OF_OPPONENT_TRENCH_RUN = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-295.5 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_RUN_BALL_1 = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 /*- ROBOT_LENGTH / 2.0*/),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_RUN_BALL_2 = new Pose2d(
            Units.feetToMeters(278.63 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_RUN_BALL_3 = new Pose2d(
            Units.feetToMeters(314.63 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_RUN_BALL_4_AND_5 = new Pose2d(
            Units.feetToMeters(378.9 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_1 = new Pose2d(
            Units.feetToMeters(240.51 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-175.8 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_2 = new Pose2d(
            Units.feetToMeters(234.17 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-160.5 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_3 = new Pose2d(
            Units.feetToMeters(227.83 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-145.2 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_1_AND_2_AND_3 = new Pose2d(
            RENDEZVOUS_POINT_BALL_1.getTranslation().getX() + 0.25 * (RENDEZVOUS_POINT_BALL_3.getTranslation().getX() - RENDEZVOUS_POINT_BALL_1.getTranslation().getX()),
            RENDEZVOUS_POINT_BALL_1.getTranslation().getY() + 0.25 * (RENDEZVOUS_POINT_BALL_3.getTranslation().getY() - RENDEZVOUS_POINT_BALL_1.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_4 = new Pose2d(
            Units.feetToMeters(234.94 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-120.79 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_5 = new Pose2d(
            Units.feetToMeters(250.25 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-114.45 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_4_AND_5 = new Pose2d(
            RENDEZVOUS_POINT_BALL_4.getTranslation().getX() + 0.75 * (RENDEZVOUS_POINT_BALL_5.getTranslation().getX() - RENDEZVOUS_POINT_BALL_4.getTranslation().getX()),
            RENDEZVOUS_POINT_BALL_4.getTranslation().getY() + 0.75 * (RENDEZVOUS_POINT_BALL_5.getTranslation().getY() - RENDEZVOUS_POINT_BALL_4.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_AND_TRENCH_RUN_SWITCH = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 - 6.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d OPPONENT_TRENCH_RUN_BALL_4_AND_5 = new Pose2d(
            Units.feetToMeters(250.35 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-295.5 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        private static final double RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_X_OFFSET = 0.0; // feet offset x from in front of power port
        private static final double RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_Y_OFFSET = -3.0; // feet offset y from in front of power port
        public static final Pose2d RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_SWITCH = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0 + RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_X_OFFSET),
            Units.feetToMeters(-94.66 / 12.0 + RENDEZVOUS_POINT_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_POINT_BALL_4_AND_5_Y_OFFSET),
            new Rotation2d(Units.degreesToRadians(180))
        );
        private static final double OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_X_OFFSET = 0.0; // feet offset x from in front of power port
        private static final double OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_Y_OFFSET = -3.0; // feet offset y from in front of power port
        public static final Pose2d OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_SWITCH = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0 + OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_X_OFFSET),
            Units.feetToMeters(-94.66 / 12.0 + OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_Y_OFFSET),
            new Rotation2d(Units.degreesToRadians(180 - Units.radiansToDegrees(Math.tan(-OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_Y_OFFSET / (10.0 + ROBOT_LENGTH / 2.0 + OPPONENT_TRENCH_RUN_AND_RENDEZVOUS_POINT_X_OFFSET)))))
        );
    }
}
