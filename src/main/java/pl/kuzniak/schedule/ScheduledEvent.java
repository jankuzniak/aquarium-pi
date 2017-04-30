package pl.kuzniak.schedule;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.kuzniak.Light;

public class ScheduledEvent {
	private int id;
	private Light light;
	private Date time;

	public static Light lightBetween(ScheduledEvent start, ScheduledEvent end, Date time) {
		Date startTime = start.getTime();
		Date endTime = end.getTime();
		// wanted to ignore start < time < end check
		// but it's again 23:59 to 00:01 scenario
		if (endTime.before(startTime)) {
			endTime = addOneDay(endTime);
		}
		if (time.before(startTime)) {
			time = addOneDay(time);
		}

		long startTimeMs = startTime.getTime();
		long endTimeMs = endTime.getTime(); // is nigh

		long period = endTimeMs - startTimeMs;
		long passed = time.getTime() - startTimeMs;

		Light lightDelta = end.getLight().sub(start.getLight());
		Light lightDeltaAtTime = lightDelta.mul(passed).div(period);
		Light lightBetween = start.getLight().add(lightDeltaAtTime);

		return lightBetween;
	}

	private static Date addOneDay(Date endTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endTime);
		calendar.add(Calendar.DATE, 1);
		endTime = calendar.getTime();
		return endTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ScheduledEvent) {
			ScheduledEvent e = (ScheduledEvent) obj;
			return (id == e.id);
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Light getLight() {
		return light;
	}

	public void setLight(Light targetLight) {
		this.light = targetLight;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
