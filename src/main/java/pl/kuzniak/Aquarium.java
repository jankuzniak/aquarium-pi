package pl.kuzniak;

public class Aquarium {

	public static void main(String[] args) throws InterruptedException {

		AquariumService service = new AquariumService();
		AquariumController controller = new AquariumController(service);

		while (true) {
			// serve
			Thread.sleep(10000);
		}
	}
}
