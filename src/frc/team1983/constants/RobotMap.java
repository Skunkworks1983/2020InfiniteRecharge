package frc.team1983.constants;

public class RobotMap
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
