package frc.team1983.util.motors;

public interface Motor
{
	void set(double throttle);
	void follow(Motor leader);
	void setBrake(boolean brake);
}
