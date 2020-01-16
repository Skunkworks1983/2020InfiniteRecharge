package frc.team1983.util.sensors;

public interface Encoder
{
    double getPositionTicks();
    double getPosition();
    double getVelocityTicks();
    double getVelocity();
}
