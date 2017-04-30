package pl.kuzniak.schedule;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import pl.kuzniak.Light;

@SuppressWarnings("deprecation")
public class ScheduledEventTest {

	private static final Date TIME_0100 = new Date(2000, 1, 1, 1, 00);
	private static final Date TIME_1200 = new Date(2000, 1, 1, 12, 00);
	private static final Date TIME_1230 = new Date(2000, 1, 1, 12, 30);
	private static final Date TIME_1300 = new Date(2000, 1, 1, 13, 00);
	private static final Date TIME_1400 = new Date(2000, 1, 1, 14, 00);

	@Test
	public void testLightBetween() {
		Schedule schedule = new Schedule();

		ScheduledEvent e12 = new ScheduledEvent();
		e12.setTime(TIME_1200);
		e12.setLight(new Light(0, 0));
		schedule.addEvent(e12);

		ScheduledEvent e14 = new ScheduledEvent();
		e14.setTime(TIME_1400);
		e14.setLight(new Light(100, 100));
		schedule.addEvent(e14);

		assertEquals(new Light(0, 0), ScheduledEvent.lightBetween(e12, e14, TIME_1200));
		assertEquals(new Light(100, 100), ScheduledEvent.lightBetween(e12, e14, TIME_1400));
		assertEquals(new Light(25, 25), ScheduledEvent.lightBetween(e12, e14, TIME_1230));
		assertEquals(new Light(50, 50), ScheduledEvent.lightBetween(e12, e14, TIME_1300));
		assertEquals(new Light(50, 50), ScheduledEvent.lightBetween(e14, e12, TIME_0100));

		e14.setLight(new Light(0, 100));
		assertEquals(new Light(0, 25), ScheduledEvent.lightBetween(e12, e14, TIME_1230));
	}
}
