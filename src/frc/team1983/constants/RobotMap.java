package frc.team1983.constants;

public class RobotMap
{
    //todo change motors for 2020
    public static final int COMPRESSOR = 7;

    public static class Drivebase
    {
        public enum Motor
        {
            LEFT1(12, false),
            LEFT2(13, false),
            LEFT3(14, false),
            RIGHT1(1, true),
            RIGHT2(2, true),
            RIGHT3(3, true);

            private int port;
            private boolean reversed;
            Motor(int port, boolean reversed)
            {
                this.port = port;
                this.reversed = reversed;
            }

            public int getPort()
            {
                return port;
            }

            public boolean isReversed()
            {
                return reversed;
            }
        }
    }

    public static class Climber
    {
        public enum Motor
        {
            Elevator1(4, false),
            Elevator2(4, false);

            private int port;
            private boolean reversed;
            Motor(int port, boolean reversed)
            {
                this.port = port;
                this.reversed = reversed;
            }

            public int getPort()
            {
                return port;
            }

            public boolean isReversed()
            {
                return reversed;
            }
        }
    }
}
