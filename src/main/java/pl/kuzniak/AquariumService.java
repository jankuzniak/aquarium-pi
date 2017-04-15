package pl.kuzniak;

public class AquariumService {

	private Light targetLight;
	private LedDriver ledDriver;

	public AquariumService() {
		targetLight = new Light();
		ledDriver = new LedDriver();
		ledDriver.init();
	}

	public Light getCurrentLight() {
		return ledDriver.getCurrentLight();
	}

	public void changeLight(int purple, int white, int time) {
		if (purple >= 0) {
			targetLight.setPurple(purple);
		}
		if (white >= 0) {
			targetLight.setWhite(white);
		}
		ledDriver.fadeLight(targetLight, time);
	}

	public void changeLight(Light newLight, int time) {
		targetLight.setPurple(newLight.purple);
		targetLight.setWhite(newLight.white);
		ledDriver.fadeLight(targetLight, time);
	}

	public void full(int time) {
		changeLight(Light.MAX, Light.MAX, time);
	}

	public void off(int time) {
		changeLight(Light.MIN, Light.MIN, time);
	}
}
