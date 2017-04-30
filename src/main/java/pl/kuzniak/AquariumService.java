package pl.kuzniak;

import java.util.Calendar;
import java.util.Date;

import pl.kuzniak.schedule.Schedule;
import pl.kuzniak.schedule.ScheduledEvent;

public class AquariumService {

	private Light targetLight;
	private LedDriver ledDriver;
	private Schedule schedule;

	public AquariumService() {
		targetLight = new Light();
		ledDriver = new LedDriver();
	}

	public Light getLight() {
		return ledDriver.getLight();
	}

	public void setLight(Light l) {
		ledDriver.setLight(l);
		targetLight = new Light(l);
	}

	public void update() {
		Date now = Calendar.getInstance().getTime();
		ScheduledEvent eventBefore = schedule.getEventBefore(now);
		ScheduledEvent eventAfter = schedule.getEventAfter(now);
		Light light = ScheduledEvent.lightBetween(eventBefore, eventAfter, now);
		setLight(light);
	}

	@Deprecated
	public void changeLight(int purple, int white, int time) {
		if (purple >= 0) {
			targetLight.setPurple(purple);
		}
		if (white >= 0) {
			targetLight.setWhite(white);
		}
		ledDriver.fadeLight(targetLight, time);
	}

	@Deprecated
	public void changeLight(Light newLight, int time) {
		targetLight.setLight(newLight);
		ledDriver.fadeLight(targetLight, time);
	}

	@Deprecated
	public void full(int time) {
		changeLight(Light.MAX, Light.MAX, time);
	}

	@Deprecated
	public void off(int time) {
		changeLight(Light.MIN, Light.MIN, time);
	}
}
