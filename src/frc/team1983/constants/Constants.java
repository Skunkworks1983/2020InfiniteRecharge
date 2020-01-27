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
        // Front bumper touching initiation line
        public static final Pose2d START_IN_FRONT_OF_TRENCH_RUN = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d START_IN_FRONT_OF_POWER_PORT = new Pose2d(
            Units.feetToMeters(10 + ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-94.66 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );

        public static final Pose2d TRENCH_RUN_BALL_1 = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 /*- ROBOT_LENGTH / 2.0*/),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d TRENCH_RUN_BALL_4_AND_5 = new Pose2d(
            Units.feetToMeters(378.9 / 12.0 - ROBOT_LENGTH / 2.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_1 = new Pose2d(
            Units.feetToMeters(250.25 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-114.45 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_2 = new Pose2d(
            Units.feetToMeters(234.94 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-120.79 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_POINT_BALL_1_AND_2 = new Pose2d(
            Units.feetToMeters(242.595 / 12.0 - ROBOT_LENGTH / 2.0 * Math.sin(Units.degreesToRadians(22.5))),
            Units.feetToMeters(-117.62 / 12.0 + ROBOT_LENGTH / 2.0 * Math.cos(Units.degreesToRadians(22.5))),
            new Rotation2d(Units.degreesToRadians(112.5))
        );
        public static final Pose2d RENDEZVOUS_TRENCH_RUN_SWITCH = new Pose2d(
            Units.feetToMeters(242.63 / 12.0 - 5.0),
            Units.feetToMeters(-27.75 / 12.0),
            new Rotation2d(Units.degreesToRadians(180))
        );
    }
}
