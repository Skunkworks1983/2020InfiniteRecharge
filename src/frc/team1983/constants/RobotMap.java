package frc.team1983.constants;

public class RobotMap
{
    public static final int COMPRESSOR = 61;

    public static class Drivebase
    {
        public static final int LEFT_1 = 1;
        public static final boolean LEFT_1_REVERSED = false;
        public static final int LEFT_2 = 2;
        public static final boolean LEFT_2_REVERSED = false;

        public static final int RIGHT_1 = 13;
        public static final boolean RIGHT_1_REVERSED = true;
        public static final int RIGHT_2 = 14;
        public static final boolean RIGHT_2_REVERSED = true;
    }

    public static class Shooter
    {
        public static final int ACCELERATOR_1 = 16;
        public static final boolean ACCELERATOR_1_REVERSED = true;

        public static final int ACCELERATOR_2 = 15;
        public static final boolean ACCELERATOR_2_REVERSED = false;

        public static final int FLYWHEEL_1 = 3;
        public static final boolean FLYWHEEL_1_REVERSED = true;

        public static final int FLYWHEEL_2 = 12;
        public static final boolean FLYWHEEL_2_REVERSED = false;

        public static final int ARTICULATION_1 = 4;
        public static final boolean ARTICULATION_1_REVERSED = true;
    }

    public static class Indexer
    {
        public static final int SHOOTER_TRANSFER_SENSOR = 0;
        public static final int INTERNAL_INDEXER_SENSOR = 2;

        public static final int COLLECTOR_TRANSFER = 5;
        public static final boolean COLLECTOR_TRANSFER_REVERSED = true;

        public static final int SHOOTER_TRANSFER = 10;
        public static final boolean SHOOTER_TRANSFER_REVERSED = false;

        public static final int INTERNAL = 8;
        public static final boolean INTERNAL_REVERSED = true;
    }

    public static class Collector
    {
        public static final int COLLECTOR_MOTOR = 6;
        public static final boolean COLLECTOR_MOTOR_REVERSED = true;

        public static final int PISTON_FORWARD = 2;
        public static final int PISTON_REVERSE = 5;
    }

    public static class ControlPanel
    {
        public static final int ROLLER = 9;
        public static final boolean ROLLER_REVERSED = false;

        public static final int PISTON_FORWARD = 1;
        public static final int PISTON_REVERSE = 4;
    }

    public static class Climber
    {
        public static final int PISTON_FORWARD = 3;
        public static final int PISTON_REVERSE = 6;
    }


}
