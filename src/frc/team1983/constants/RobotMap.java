package frc.team1983.constants;

public class RobotMap
{
    public static final int COMPRESSOR = 0; //TODO: fix fix fix fix

    public static class Drivebase
    {
        public static final int LEFT_1 = 12;
        public static final boolean LEFT_1_REVERSED = false;
        public static final int LEFT_2 = 13;
        public static final boolean LEFT_2_REVERSED = false;
        public static final int LEFT_3 = 14;
        public static final boolean LEFT_3_REVERSED = false;

        public static final int RIGHT_1 = 1;
        public static final boolean RIGHT_1_REVERSED = true;
        public static final int RIGHT_2 = 2;
        public static final boolean RIGHT_2_REVERSED = true;
        public static final int RIGHT_3 = 3;
        public static final boolean RIGHT_3_REVERSED = true;
    }

    public static class Collector //TODO: pls fix before we do anything (ports)
    {
        public static final int ROLLER = 0;
        public static final boolean ROLLER_REVERSED = false;

        public static final int LEFT = 0;
        public static final boolean LEFT_REVERSED = false;

        public static final int RIGHT = 0;
        public static final boolean RIGHT_REVERSED = true;

        public static final int PISTON_FORWARD = 0;
        public static final int PISTON_REVERSE = 1;

    }


    public static class Indexer //TODO: SET CORRECT PORTS AND SETTINGS
    {
        public static final int SENSOR = 0;

        public static final int COLLECTOR_TRANSFER = 0;
        public static final boolean COLLECTOR_TRANSFER_REVERSED = false;


        public static final int SHOOTER_TRANSFER = 0;
        public static final boolean SHOOTER_TRANSFER_REVERSED = false;

    }
}
