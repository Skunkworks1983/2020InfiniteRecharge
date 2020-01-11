package frc.team1983.subsystems;

public class Motor extends com.revrobotics.CANSparkMax
{
    private boolean reversed;

    public Motor(int port, boolean reversed)
    {
        super(port, MotorType.kBrushless);
        this.reversed = reversed;
    }

    public void set(double value)
    {
        super.set(reversed ? -value : value);
    }
}
