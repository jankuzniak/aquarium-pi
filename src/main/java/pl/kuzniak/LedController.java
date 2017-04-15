package pl.kuzniak;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class LedController {
	// https://projects.drogon.net/raspberry-pi/wiringpi/pins/
	private static final int WHITE_PIN_NUMBER = 26;
	private static final int PURPLE_PIN_NUMBER = 27;

	private Light light;

	public void init() {
		light = new Light();
		// initialize wiringPi library, this is needed for PWM
		Gpio.wiringPiSetup();

		// the range is set like (min=0 ; max=100)
		SoftPwm.softPwmCreate(WHITE_PIN_NUMBER, 0, 100);
		SoftPwm.softPwmCreate(PURPLE_PIN_NUMBER, 0, 100);
	}

	public void setLight(Light newLight) {
		System.out.println("writing " + newLight);
		SoftPwm.softPwmWrite(WHITE_PIN_NUMBER, newLight.white);
		SoftPwm.softPwmWrite(PURPLE_PIN_NUMBER, newLight.purple);
		light.white = newLight.white;
		light.purple = newLight.purple;
	}

	public void fadeLight(Light newLight, int seconds) {
		System.out.println("writing " + newLight);
		if (seconds > 0) {
			float intervals = 100.0f * seconds;
			float deltaWhite = (newLight.white - light.white) / intervals;
			float deltaPurple = (newLight.purple - light.purple) / intervals;
			for (int i = 0; i < seconds * 100; i++) {
				int white = light.white + Math.round(i * deltaWhite);
				white = Math.min(100, white);
				white = Math.max(0, white);
				int purple = light.purple + Math.round(i * deltaPurple);
				purple = Math.min(100, purple);
				purple = Math.max(0, purple);
				// System.out.printf("writing %3d / %3d\n", white, purple);
				SoftPwm.softPwmWrite(WHITE_PIN_NUMBER, white);
				SoftPwm.softPwmWrite(PURPLE_PIN_NUMBER, purple);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		light.white = newLight.white;
		light.purple = newLight.purple;
		SoftPwm.softPwmWrite(WHITE_PIN_NUMBER, light.white);
		SoftPwm.softPwmWrite(PURPLE_PIN_NUMBER, light.purple);
		System.out.println("done writing " + newLight);
	}
}
