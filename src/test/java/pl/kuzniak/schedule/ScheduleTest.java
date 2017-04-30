package pl.kuzniak.schedule;

import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class ScheduleTest {

	private static final Date TIME_1000 = new Date(2000, 1, 1, 10, 00);
	private static final Date TIME_1100 = new Date(2000, 1, 1, 11, 00);
	private static final Date TIME_1200 = new Date(2000, 1, 1, 12, 00);
	private static final Date TIME_1230 = new Date(2000, 1, 1, 12, 30);
	private static final Date TIME_1300 = new Date(2000, 1, 1, 13, 00);
	private static final Date TIME_1400 = new Date(2000, 1, 1, 14, 00);

	@Test
	public void testAddEventUniqueId() {
		Schedule schedule = new Schedule();

		ScheduledEvent e12 = new ScheduledEvent();
		e12.setTime(TIME_1200);
		schedule.addEvent(e12);
		assertEquals(0, e12.getId());

		ScheduledEvent e13 = new ScheduledEvent();
		e13.setTime(TIME_1300);
		schedule.addEvent(e13);
		assertEquals(1, e13.getId());

		ScheduledEvent e11 = new ScheduledEvent();
		e11.setTime(TIME_1100);
		schedule.addEvent(e11);
		assertEquals(2, e11.getId());

		schedule.removeEvent(e13);
		schedule.addEvent(e13);
		assertEquals(3, e13.getId());
	}

	@Test
	public void testGetEventBefore() {
		Schedule schedule = new Schedule();
		ScheduledEvent e12 = new ScheduledEvent();
		e12.setTime(TIME_1200);
		schedule.addEvent(e12);

		assertEquals(e12, schedule.getEventBefore(TIME_1100));
		assertEquals(e12, schedule.getEventBefore(TIME_1200));
		assertEquals(e12, schedule.getEventBefore(TIME_1300));

		ScheduledEvent e13 = new ScheduledEvent();
		e13.setTime(TIME_1300);
		schedule.addEvent(e13);

		assertEquals(e13, schedule.getEventBefore(TIME_1100));
		assertEquals(e12, schedule.getEventBefore(TIME_1200));
		assertEquals(e12, schedule.getEventBefore(TIME_1230));
		assertEquals(e13, schedule.getEventBefore(TIME_1300));
		assertEquals(e13, schedule.getEventBefore(TIME_1400));

		ScheduledEvent e11 = new ScheduledEvent();
		e11.setTime(TIME_1100);
		schedule.addEvent(e11);

		assertEquals(e13, schedule.getEventBefore(TIME_1000));
		assertEquals(e11, schedule.getEventBefore(TIME_1100));
		assertEquals(e12, schedule.getEventBefore(TIME_1200));
		assertEquals(e12, schedule.getEventBefore(TIME_1230));
		assertEquals(e13, schedule.getEventBefore(TIME_1300));
		assertEquals(e13, schedule.getEventBefore(TIME_1400));
	}

	@Test
	public void testGetEventAfter() {
		Schedule schedule = new Schedule();
		ScheduledEvent e12 = new ScheduledEvent();
		e12.setTime(TIME_1200);
		schedule.addEvent(e12);

		assertEquals(e12, schedule.getEventAfter(TIME_1100));
		assertEquals(e12, schedule.getEventAfter(TIME_1200));
		assertEquals(e12, schedule.getEventAfter(TIME_1300));

		ScheduledEvent e13 = new ScheduledEvent();
		e13.setTime(TIME_1300);
		schedule.addEvent(e13);

		assertEquals(e12, schedule.getEventAfter(TIME_1100));
		assertEquals(e13, schedule.getEventAfter(TIME_1200));
		assertEquals(e13, schedule.getEventAfter(TIME_1230));
		assertEquals(e12, schedule.getEventAfter(TIME_1300));
		assertEquals(e12, schedule.getEventAfter(TIME_1400));

		ScheduledEvent e11 = new ScheduledEvent();
		e11.setTime(TIME_1100);
		schedule.addEvent(e11);

		assertEquals(e11, schedule.getEventAfter(TIME_1000));
		assertEquals(e12, schedule.getEventAfter(TIME_1100));
		assertEquals(e13, schedule.getEventAfter(TIME_1200));
		assertEquals(e13, schedule.getEventAfter(TIME_1230));
		assertEquals(e11, schedule.getEventAfter(TIME_1300));
		assertEquals(e11, schedule.getEventAfter(TIME_1400));
	}
}
