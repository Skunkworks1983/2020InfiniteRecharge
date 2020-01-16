package frc.team1983.util.sensors;

public interface Encoder
{
    void zero();
    double getPositionTicks();
    double getPosition();
    double getVelocityTicks();
    double getVelocity();
    double getConversionRatio();
    void setConversionRatio(double conversionRatio);
}
