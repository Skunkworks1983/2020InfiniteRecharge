package frc.team1983.constants;

public class RobotMap //TODO: all of these values have yet to be finalized
{
    public static final int COMPRESSOR = 0; //TODO: find the correct port

    //our 2020 drivebase only utilizes 4 neos
    // 1 and 2 are left, 13 and 14 are right
    //TODO: change this once we get a final 2020 robot
    public static class Drivebase
    {
    //    public static final int LEFT_1 = 12;
    //    public static final boolean LEFT_1_REVERSED = false;
        public static final int LEFT_1 = 1;
        public static final boolean LEFT_1_REVERSED = false;
        public static final int LEFT_2 = 2;
        public static final boolean LEFT_2_REVERSED = false;

        public static final int RIGHT_1 = 13;
        public static final boolean RIGHT_1_REVERSED = true;
        public static final int RIGHT_2 = 14;
        public static final boolean RIGHT_2_REVERSED = true;
    //    public static final int RIGHT_3 = 3;
    //    public static final boolean RIGHT_3_REVERSED = true;
    }

    public static class Shooter
    {
        public static final int ACCELERATOR_1 = 16;
        public static final boolean ACCELERATOR_1_REVERSED = false;

        public static final int ACCELERATOR_2 = 15;
        public static final boolean ACCELERATOR_2_REVERSED = true;

        public static final int FLYWHEEL_1 = 3;
        public static final boolean FLYWHEEL_1_REVERSED = false;

        public static final int FLYWHEEL_2 = 12;
        public static final boolean FLYWHEEL_2_REVERSED = true;

        public static final int ARTICULATION_1 = 4;
        public static final boolean ARTICULATION_1_REVERSED = true;

        //public static final int ARTICULATION_2 = 11;
        //public static final boolean ARTICULATION_2_REVERSED = false;
    }

    public static class Indexer
    {
        public static final int SENSOR = 0;

        public static final int COLLECTOR_TRANSFER = 5;
        public static final boolean COLLECTOR_TRANSFER_REVERSED = false;

        public static final int SHOOTER_TRANSFER = 10;
        public static final boolean SHOOTER_TRANSFER_REVERSED = false;
    }

    public static class Collector
    {
        public static final int ROLLER = 6; //TODO: change when we have time
        public static final boolean ROLLER_REVERSED = false;

        public static final int PISTON_FORWARD = 0;
        public static final int PISTON_REVERSE = 1;
    }

    public static class ControlPanel
    {
        public static final int ROLLER = 9;
        public static final boolean ROLLER_REVERSED = false;

        public static final int PISTON_FORWARD = 0;
        public static final int PISTON_REVERSE = 1;
    }

    public static class Climber
    {
        public static final int PISTON_FORWARD = 0;
        public static final int PISTON_REVERSE = 1;
    }


}
