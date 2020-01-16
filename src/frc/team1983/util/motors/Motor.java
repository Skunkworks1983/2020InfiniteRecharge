package frc.team1983.util.motors;

public interface Motor
{
    void set(double output);
    void setBrake(boolean brake);
    void setCurrentLimit(int limit);
}
