package pl.kuzniak;

import java.util.Scanner;
import java.util.Set;

import spark.Request;

import static spark.Spark.*;

public class DimLedPwm {

	private static LedController led;
	private static Light light;

	public static void main(String[] args) throws InterruptedException {

		light = new Light();
		led = new LedController();
		led.init();

		setupSpark();

		while (true) {
			// pulse();
			light.off();
			System.out.println("Choose colour:\n");
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			switch (i) {
			case 1:
				light.white = 100; // white
				break;
			case 2:
				light.purple = 100; // purple
				break;
			// case 3:
			// light.b = 100;
			// break;
			// case 4:
			// light.w = 100;
			// break;
			}
			led.setLight(light);
		}
	}

	private static void setupSpark() {
		get("/white/:time/:brightness", (req, res) -> {
			System.out.println(req.pathInfo());
			int b = getBrightnessParameter(req);
			int t = getTimeParameter(req);
			light.off(Colour.PURPLE);
			light.on(Colour.WHITE, b);
			led.fadeLight(light, t);
			return "white on";
		});
		get("/purple/:time/:brightness", (req, res) -> {
			int t = getTimeParameter(req);
			int b = getBrightnessParameter(req);
			light.off(Colour.WHITE);
			light.on(Colour.PURPLE, b);
			led.fadeLight(light, t);
			return "purple on";
		});
		get("/full", (req, res) -> {
			light.on(Colour.WHITE);
			light.on(Colour.PURPLE);
			led.fadeLight(light, 5);
			return "purple on";
		});
		get("/on", (req, res) -> {
			String brightness = req.queryParams("b");
			int b = Integer.parseInt(brightness);
			b = Light.normalise(b);
			light.on(Colour.WHITE, b);
			light.on(Colour.PURPLE, b);
			led.fadeLight(light, 5);
			return "purple on";
		});
		get("/off", (req, res) -> {
			light.off(Colour.WHITE);
			light.off(Colour.PURPLE);
			led.fadeLight(light, 5);
			return "purple on";
		});
	}

	public static int getBrightnessParameter(Request req) {
		for (String key : req.params().keySet()) {
			System.out.println("key: " + key + " = " + req.params(key));
		}
		String brightness = req.params(":brightness");
		System.out.println("got brightness: " + brightness);
		return Integer.parseInt(brightness);
	}

	public static int getTimeParameter(Request req) {
		String time = req.params(":time");
		System.out.println("got time: " + time);
		return Integer.parseInt(time);
	}

	// private static void pulse() throws InterruptedException {
	// Calendar calendar = Calendar.getInstance();
	// int second = calendar.get(Calendar.SECOND);
	// int brightness = second * 100 / 60;
	// // light.white = light.purple = light.b = light.w = brightness;
	// led.writeColour(light);
	// Thread.sleep(250);
	// }

	// private static int test(int counter) throws InterruptedException {
	// while (counter < 3) {
	// // fade LED to fully ON
	// for (int i = 0; i <= 100; i++) {
	// // softPwmWrite(int pin, int value)
	// // This updates the PWM value on the given pin. The value is
	// // checked to be in-range and pins
	// // that haven't previously been initialized via softPwmCreate
	// // will be silently ignored.
	// SoftPwm.softPwmWrite(R_PIN_NUMBER, i);
	// Thread.sleep(25);
	// }
	// // fade LED to fully OFF
	// for (int i = 100; i >= 0; i--) {
	// SoftPwm.softPwmWrite(R_PIN_NUMBER, i);
	// Thread.sleep(25);
	// }
	// counter++;
	// }
	// return counter;
	// }
}
