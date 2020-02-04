package frc.team1983.util.sensors;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class NavX extends AHRS
{
    public static final int NAVX_HEADING_SIGN = -1;

    private double offsetHeading = 0;
    private double offsetPitch = 0;
    private double offsetRoll = 0;

    public NavX()
    {
        super(SPI.Port.kMXP);
    }

    public Rotation2d getHeading()
    {
        return Rotation2d.fromDegrees(getAngle() * NAVX_HEADING_SIGN + offsetHeading);
    }

    public float getPitch()
    {
        return super.getRoll() + (float) offsetPitch;
    }

    public float getRoll()
    {
        return super.getPitch() + (float) offsetRoll;
    }

    public void setHeading(double heading)
    {
        offsetHeading = heading - (getAngle() * NAVX_HEADING_SIGN);
    }

    public void setPitch(float pitch)
    {
        offsetPitch = pitch - getPitch();
    }

    public void setRoll(float roll)
    {
        offsetRoll = roll - getRoll();
    }

    public void reset()
    {
        setHeading(0);
        setPitch(0);
        setRoll(0);
    }
}