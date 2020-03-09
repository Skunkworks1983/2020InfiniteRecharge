package frc.team1983.commands;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class UT_TargetAlignment
{
	@Test
	public void wrapAngleWrapsAnglesGreaterThan180()
	{
		Assert.assertThat(TargetAlignment.wrapAngle(181), is(-179.0));
		Assert.assertThat(TargetAlignment.wrapAngle(361), is(1.0));
		Assert.assertThat(TargetAlignment.wrapAngle(541), is(-179.0));
		Assert.assertThat(TargetAlignment.wrapAngle(721), is(1.0));
	}

	@Test
	public void wrapAngleWrapsAnglesLessThanNegative180()
	{
		Assert.assertThat(TargetAlignment.wrapAngle(-181), is(179.0));
		Assert.assertThat(TargetAlignment.wrapAngle(-361), is(-1.0));
		Assert.assertThat(TargetAlignment.wrapAngle(-541), is(179.0));
		Assert.assertThat(TargetAlignment.wrapAngle(-721), is(-1.0));
	}
}