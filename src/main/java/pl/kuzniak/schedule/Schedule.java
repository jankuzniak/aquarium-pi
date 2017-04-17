package pl.kuzniak.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Schedule {
	private List<ScheduledEvent> events;

	private SortedSet<ScheduledEvent> eventsByTime;

	public Schedule() {
		events = new ArrayList<>();
		eventsByTime = new TreeSet<>(new ScheduleEventTimeComparator());
	}

	public ScheduledEvent getEventBefore(Date time) {
		if (eventsByTime.isEmpty()) {
			return null; // shall not happen!
		}

		// pickup the last in case none of the entries match;
		// scenario: time is 0:01 and the previous event was yesterday (at 23:00)
		ScheduledEvent lastMatching = eventsByTime.last();
		for (ScheduledEvent event : eventsByTime) {
			// the value 0 if the argument Date is equal to this Date;
			// a value less than 0 if this Date is before the Date argument;
			// a value greater than 0 if this Date is after the Date argument.
			if (event.getTime().compareTo(time) <= 0) {
				lastMatching = event;
			}
		}
		return lastMatching;
	}

	// CRUD

	private int getNewId() {
		if (events.isEmpty()) {
			return 0;
		} else {
			ScheduledEvent lastEvent = events.get(events.size() - 1);
			return lastEvent.getId() + 1;
		}
	}

	public void addEvent(ScheduledEvent event) {
		event.setId(getNewId());
		events.add(event);
		eventsByTime.add(event);
	}

	public void removeEvent(ScheduledEvent event) {
		events.remove(event);
		eventsByTime.remove(event);
	}

	// Getters and Setters

	public List<ScheduledEvent> getEvents() {
		return events;
	}

	public SortedSet<ScheduledEvent> getEventsByTime() {
		return eventsByTime;
	}
}
