package frc.team1983.util.sensors;

public interface Encoder
{
    void configure();
    double getPosition();
    double getVelocity();
}
