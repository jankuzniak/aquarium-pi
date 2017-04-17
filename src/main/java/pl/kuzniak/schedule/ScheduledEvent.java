package pl.kuzniak.schedule;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.kuzniak.Light;

public class ScheduledEvent {
	private int id;
	private Light targetLight;
	private Date time;

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

	public Light getTargetLight() {
		return targetLight;
	}

	public void setTargetLight(Light targetLight) {
		this.targetLight = targetLight;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
