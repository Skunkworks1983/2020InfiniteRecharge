package frc.team1983.commands;

import frc.team1983.services.OI;
import frc.team1983.subsystems.Drivebase;
import frc.team1983.util.motors.ControlMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UT_RunGyroDrive
{
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private Drivebase drivebase;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private OI oi;

	private RunGyroDrive runGyroDrive;

	@Before
	public void setup()
	{
		initMocks(this);

		runGyroDrive = new RunGyroDrive(drivebase, oi);

		when(oi.getButton(OI.Joysticks.LEFT, 1).get()).thenReturn(false);
		when(oi.getLeftX()).thenReturn(0.0);
		when(oi.getRightY()).thenReturn(0.0);

		when(drivebase.getHeading().getDegrees()).thenReturn(0.0);

		runGyroDrive.initialize();
	}

	@Test
	public void executeSetsOutputToZeroWhenJoysticksAreZero()
	{
		runGyroDrive.execute();

		verify(drivebase).setLeft(ControlMode.Throttle, 0.0);
		verify(drivebase).setRight(ControlMode.Throttle, 0.0);
	}

	@Test
	public void executeDoesNotTurnWhenLeftJoystickIsZeroAndHeadingErrorIsZero()
	{
		when(oi.getRightY()).thenReturn(1.0);

		when(drivebase.getHeading().getDegrees()).thenReturn(0.0);

		runGyroDrive.execute();

		ArgumentCaptor<Double> leftOutput = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Double> rightOutput = ArgumentCaptor.forClass(Double.class);

		verify(drivebase).setLeft(eq(ControlMode.Throttle), leftOutput.capture());
		verify(drivebase).setRight(eq(ControlMode.Throttle), rightOutput.capture());

		Assert.assertThat(leftOutput.getValue(), is(rightOutput.getValue()));
	}

	@Test
	public void executeOnlyTurnsWhenRightJoystickIsZero()
	{
		when(oi.getLeftX()).thenReturn(0.5);

		when(drivebase.getHeading().getDegrees()).thenReturn(0.0);

		runGyroDrive.execute();

		ArgumentCaptor<Double> leftOutput = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Double> rightOutput = ArgumentCaptor.forClass(Double.class);

		verify(drivebase).setLeft(eq(ControlMode.Throttle), leftOutput.capture());
		verify(drivebase).setRight(eq(ControlMode.Throttle), rightOutput.capture());

		Assert.assertThat(leftOutput.getValue(), is(-rightOutput.getValue()));
	}

	@Test
	public void executeDoesNotTurnWhenLeftJoystickIsDeadzoned()
	{
		when(oi.getLeftX()).thenReturn(RunGyroDrive.JOYSTICK_DEADZONE / 2.0);
		when(oi.getRightY()).thenReturn(0.5);

		when(drivebase.getHeading().getDegrees()).thenReturn(0.0);

		runGyroDrive.execute();

		ArgumentCaptor<Double> leftOutput = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Double> rightOutput = ArgumentCaptor.forClass(Double.class);

		verify(drivebase).setLeft(eq(ControlMode.Throttle), leftOutput.capture());
		verify(drivebase).setRight(eq(ControlMode.Throttle), rightOutput.capture());

		Assert.assertThat(leftOutput.getValue(), is(rightOutput.getValue()));
	}
}
