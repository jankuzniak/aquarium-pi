package pl.kuzniak.schedule;

import java.util.Comparator;

public class ScheduleEventTimeComparator implements Comparator<ScheduledEvent> {

	// using Comparator instead of Comparable as I'm overriding equals on id and
	// comparing on time
	@Override
	public int compare(ScheduledEvent o1, ScheduledEvent o2) {
		// blissfully ignoring null-checks
		return o1.getTime().compareTo(o2.getTime());
	}
}
