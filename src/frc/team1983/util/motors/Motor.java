package frc.team1983.util.motors;

public interface Motor
{
	void zero();
	void set(double throttle);
	void follow(Motor leader);
	void setBrake(boolean brake);
	double getPositionTicks();
	double getPosition();
	double getVelocityTicks();
	double getVelocity();
	double getConversionRatio();
	void setConversionRatio(double conversionRatio);
}
