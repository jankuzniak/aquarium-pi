package pl.kuzniak;

import static spark.Spark.*;

import com.google.gson.Gson;

import spark.Request;
import spark.Spark;

public class AquariumController {

	private AquariumService aquariumService;
	private Gson gson;

	public AquariumController(AquariumService service) {
		this.aquariumService = service;
		gson = new Gson();

		//Spark.port(80);
		Spark.staticFileLocation("/public");
		// Map<String, String> map = new HashMap<>();
		// map.put("body", "Hello World");
		// hello.hbs file is in resources/templates directory
		// get("/", (rq, rs) -> new ModelAndView(map, "home.hbs"), new
		// HandlebarsTemplateEngine());

		get("/api/light", (req, res) -> {
			return aquariumService.getLight();
		}, gson::toJson);

		post("/api/light", (req, res) -> {
			Light newLight = gson.fromJson(req.body(), Light.class);
			aquariumService.changeLight(newLight, 0);
			return newLight;
		}, gson::toJson);

		registerSimpleApi();
		registerColourFades();
	}

	private void registerColourFades() {
		get("/white/:time/:brightness", (req, res) -> {
			System.out.println(req.pathInfo());
			int b = getBrightnessFromRequest(req);
			int t = getTimeFromRequest(req);
			aquariumService.changeLight(-1, b, t);
			return "white on";
		});
		get("/purple/:time/:brightness", (req, res) -> {
			int t = getTimeFromRequest(req);
			int b = getBrightnessFromRequest(req);
			aquariumService.changeLight(b, -1, t);
			return "purple on";
		});
	}

	private void registerSimpleApi() {
		get("/full", (req, res) -> {
			aquariumService.full(5);
			return "full on";
		});
		get("/on", (req, res) -> {
			String brightness = req.queryParams("b");
			int b = Integer.parseInt(brightness);
			aquariumService.changeLight(b, b, 5);
			return "on";
		});
		get("/off", (req, res) -> {
			aquariumService.off(5);
			return "off";
		});
	}

	public static int getTimeFromRequest(Request req) {
		String time = req.params(":time");
		System.out.println("got time: " + time);
		return Integer.parseInt(time);
	}

	public static int getBrightnessFromRequest(Request req) {
		String brightness = req.params(":brightness");
		System.out.println("got brightness: " + brightness);
		return Integer.parseInt(brightness);
	}
}
