package frc.team1983.util.motors;

public interface Motor
{
	void set(ControlMode controlMode, double value);
	void follow(Motor leader);
	default void follow(Motor leader, boolean reversed)
	{
		follow(leader);
	};
	void setBrake(boolean brake);
	boolean isReversed();
}
