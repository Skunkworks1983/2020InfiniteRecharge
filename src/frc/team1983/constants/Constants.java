package frc.team1983.constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;

public class Constants
{
    // 2019
//    public static final double ROBOT_WIDTH = 34.0 / 12.0; // feet
//    public static final double ROBOT_LENGTH = 37.0 /12.0; // feet
//    public static final double TRACK_WIDTH = 24.75 / 12.0; // feet
    // 2020
    public static final double ROBOT_WIDTH = 34.0 / 12.0; // feet
    public static final double ROBOT_LENGTH = 38.0 /12.0; // feet
    public static final double TRACK_WIDTH = 23.75 / 12.0; // feet

    /**
     * All poses are in meters
     *
     * IN_FRONT_OF is a starting pose on the initiation line
     * SWITCH is an intermediate pose between two other poses, the poses are separated by AND
     * OFFSET is an x or y offset from another pose
     * TRENCH ball 1 is closest to the initiation line, ball 4 and 5 are furthest
     * RENDEZVOUS ball 1 is closest to the center of the rendezvous point and increase clockwise
     * OPPONENT_TRENCH ball 1 is closest to the opponent initiation line, ball 4 and 5 are furthest
     */
    public static class Pose
    {
        // Pose for the port target faces into alliance wall
        public static final Pose2d PORT_TARGET = new Pose2d(
            0.0,
            Units.feetToMeters(-94.66 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        // Front bumper touching initiation line
        public static final Pose2d IN_FRONT_OF_TRENCH = new Pose2d(
            Units.feetToMeters((10 + 1.0 / 12.0) + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d IN_FRONT_OF_PORT = new Pose2d(
            Units.feetToMeters((10 + 1.0 / 12.0) + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-94.66 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d IN_FRONT_OF_OPPONENT_TRENCH = new Pose2d(
            Units.feetToMeters((10 + 1.0 / 12.0) + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-295.5 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_BALL_1 = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_BALL_2 = new Pose2d(
            Units.feetToMeters(278.63 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_BALL_3 = new Pose2d(
            Units.feetToMeters(314.63 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_BALL_4_AND_5 = new Pose2d(
            Units.feetToMeters(378.9 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d RENDEZVOUS_BALL_1 = new Pose2d(
            Units.feetToMeters(240.51 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-175.8 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_2 = new Pose2d(
            Units.feetToMeters(234.17 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-160.5 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_3 = new Pose2d(
            Units.feetToMeters(227.83 / 12.0 - ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-145.2 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_1_AND_2_AND_3 = new Pose2d(
            RENDEZVOUS_BALL_1.getTranslation().getX() + 0.25 * (RENDEZVOUS_BALL_3.getTranslation().getX() - RENDEZVOUS_BALL_1.getTranslation().getX()),
            RENDEZVOUS_BALL_1.getTranslation().getY() + 0.25 * (RENDEZVOUS_BALL_3.getTranslation().getY() - RENDEZVOUS_BALL_1.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_4 = new Pose2d(
            Units.feetToMeters(234.94 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-120.79 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_5 = new Pose2d(
            Units.feetToMeters(250.25 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-114.45 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_4_AND_5 = new Pose2d(
            RENDEZVOUS_BALL_4.getTranslation().getX() + 0.75 * (RENDEZVOUS_BALL_5.getTranslation().getX() - RENDEZVOUS_BALL_4.getTranslation().getX()),
            RENDEZVOUS_BALL_4.getTranslation().getY() + 0.75 * (RENDEZVOUS_BALL_5.getTranslation().getY() - RENDEZVOUS_BALL_4.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        private static final double RENDEZVOUS_OFFSET_DISTANCE = 1.0; // feet offset from edge of rendezvous point
        public static final Pose2d RENDEZVOUS_BALL_1_OFFSET = new Pose2d(
            Units.feetToMeters(240.51 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-175.8 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_2_OFFSET = new Pose2d(
            Units.feetToMeters(234.17 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-160.5 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_3_OFFSET = new Pose2d(
            Units.feetToMeters(227.83 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.cos(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-145.2 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.sin(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_1_AND_2_AND_3_OFFSET = new Pose2d(
            RENDEZVOUS_BALL_1_OFFSET.getTranslation().getX() + 0.25 * (RENDEZVOUS_BALL_3_OFFSET.getTranslation().getX() - RENDEZVOUS_BALL_1_OFFSET.getTranslation().getX()),
            RENDEZVOUS_BALL_1_OFFSET.getTranslation().getY() + 0.25 * (RENDEZVOUS_BALL_3_OFFSET.getTranslation().getY() - RENDEZVOUS_BALL_1_OFFSET.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(-157.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_4_OFFSET = new Pose2d(
            Units.feetToMeters(234.94 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-120.79 / 12.0 + (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_5_OFFSET = new Pose2d(
            Units.feetToMeters(250.25 / 12.0 - (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-114.45 / 12.0 + (ROBOT_LENGTH / 2.0 + RENDEZVOUS_OFFSET_DISTANCE) * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_BALL_4_AND_5_OFFSET = new Pose2d(
            RENDEZVOUS_BALL_4_OFFSET.getTranslation().getX() + 0.75 * (RENDEZVOUS_BALL_5_OFFSET.getTranslation().getX() - RENDEZVOUS_BALL_4_OFFSET.getTranslation().getX()),
            RENDEZVOUS_BALL_4_OFFSET.getTranslation().getY() + 0.75 * (RENDEZVOUS_BALL_5_OFFSET.getTranslation().getY() - RENDEZVOUS_BALL_4_OFFSET.getTranslation().getY()),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        private static final double RENDEZVOUS_AND_TRENCH_SWITCH_OFFSET = 6.0; // feet offset from trench ball 1
        public static final Pose2d RENDEZVOUS_AND_TRENCH_SWITCH = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 - RENDEZVOUS_AND_TRENCH_SWITCH_OFFSET),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d OPPONENT_TRENCH_BALL_4_AND_5 = new Pose2d(
            Units.feetToMeters(250.35 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-295.5 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        private static final double RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_X_OFFSET = 0.0; // feet offset x from in front of power port
        private static final double RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_Y_OFFSET = -3.0; // feet offset y from in front of power port
        public static final Pose2d RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_SWITCH = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0 + RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_X_OFFSET),
            Units.feetToMeters(-94.66 / 12.0 + RENDEZVOUS_BALL_1_AND_2_AND_3_AND_RENDEZVOUS_BALL_4_AND_5_Y_OFFSET),
            new Rotation2d(Units.degreesToRadians(180))
        );
        private static final double OPPONENT_TRENCH_AND_RENDEZVOUS_X_OFFSET = 0.0; // feet offset x from in front of power port
        private static final double OPPONENT_TRENCH_AND_RENDEZVOUS_Y_OFFSET = -3.0; // feet offset y from in front of power port
        public static final Pose2d OPPONENT_TRENCH_AND_RENDEZVOUS_SWITCH = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0 + OPPONENT_TRENCH_AND_RENDEZVOUS_X_OFFSET),
            Units.feetToMeters(-94.66 / 12.0 + OPPONENT_TRENCH_AND_RENDEZVOUS_Y_OFFSET),
            new Rotation2d(Units.degreesToRadians(180 - Units.radiansToDegrees(Math.tan(-OPPONENT_TRENCH_AND_RENDEZVOUS_Y_OFFSET / (10.0 + ROBOT_LENGTH / 2.0 + OPPONENT_TRENCH_AND_RENDEZVOUS_X_OFFSET)))))
        );
        private static final double SHOOT_IN_FRONT_OF_PORT_OFFSET = 4.0; // feet offset from initiation line
        public static final Pose2d SHOOT_IN_FRONT_OF_PORT = new Pose2d(
            Units.feetToMeters((SHOOT_IN_FRONT_OF_PORT_OFFSET + 10 + 1.0 / 12.0) + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-94.66 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final double SHOOT_IN_FRONT_OF_PORT_TOWARD_TRENCH_BALL_1_HEADING = -135.0;
        public static final Pose2d SHOOT_IN_FRONT_OF_PORT_TOWARD_TRENCH_BALL_1 = new Pose2d(
        	SHOOT_IN_FRONT_OF_PORT.getTranslation(),
            new Rotation2d(Units.degreesToRadians(SHOOT_IN_FRONT_OF_PORT_TOWARD_TRENCH_BALL_1_HEADING))
        );
        public static final double TRENCH_BALL_1_FROM_SHOOT_IN_FRONT_OF_PORT_HEADING = -135.0;
        public static final Pose2d TRENCH_BALL_1_FROM_SHOOT_IN_FRONT_OF_PORT = new Pose2d(
            Units.feetToMeters(242.63 / 12.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(TRENCH_BALL_1_FROM_SHOOT_IN_FRONT_OF_PORT_HEADING))
        );
        public static final Pose2d BARREL_RACING_START_ZONE = new Pose2d(
            Units.feetToMeters(5 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(7.5),
            new Rotation2d(0)
        );
        public static final Pose2d BARREL_RACING_FINISH_ZONE = new Pose2d(
            Units.feetToMeters(5 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(7.5),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d SLALOM_START_ZONE = new Pose2d(
            Units.feetToMeters(5 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(2.5),
            new Rotation2d(0)
        );
        public static final Pose2d SLALOM_FINISH_ZONE = new Pose2d(
            Units.feetToMeters(5 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(7.5),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d BOUNCE_START_ZONE = new Pose2d(
            Units.feetToMeters(5 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(7.5),
            new Rotation2d(0)
        );
        public static final Pose2d BOUNCE_A3 = new Pose2d(
            Units.feetToMeters(7.5 - 0.5),
            Units.feetToMeters(12.5 - 1),
            new Rotation2d(Units.degreesToRadians(90))
        );
        public static final Pose2d BOUNCE_A3_E5_WAYPOINT = new Pose2d(
            Units.feetToMeters(8.5),
            Units.feetToMeters(6.5),
            new Rotation2d(Units.degreesToRadians(115))
        );
        public static final Pose2d BOUNCE_E5 = new Pose2d(
            Units.feetToMeters(12.5),
            Units.feetToMeters(2.5 + 0.5),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d BOUNCE_A6 = new Pose2d(
            Units.feetToMeters(15 - 0.5),
            Units.feetToMeters(12.5 - 1),
            new Rotation2d(Units.degreesToRadians(-90))
        );
        public static final Pose2d BOUNCE_E7 = new Pose2d(
            Units.feetToMeters(18.5 - 0.5),
            Units.feetToMeters(2.5 + 0.5),
            new Rotation2d(0)
        );
        public static final Pose2d BOUNCE_A9 = new Pose2d(
            Units.feetToMeters(22.5 - 0.5),
            Units.feetToMeters(12.5 - 1),
            new Rotation2d(Units.degreesToRadians(90))
        );
        public static final Pose2d BOUNCE_FINISH_ZONE = new Pose2d(
            Units.feetToMeters(25 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(7.5 + 1),
            new Rotation2d(Units.degreesToRadians(180))
        );
    }
}
