package frc.team1983.util.motors;

public interface Motor
{
	void set(ControlMode controlMode, double value);
	void follow(Motor leader);
	void setBrake(boolean brake);
	void setVoltageRamp(double value);
}
