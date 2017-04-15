package pl.kuzniak;

public class Light {
	public static final int MIN = 0;
	public static final int MAX = 100;

	public int white;
	public int purple;

	public static int normalise(int brightness) {
		return Math.min(MAX, Math.max(MIN, brightness));
	}

	public void off() {
		off(Colour.WHITE);
		off(Colour.PURPLE);
	}

	public void on() {
		on(Colour.WHITE);
		on(Colour.PURPLE);
	}

	public void on(Colour c) {
		switch (c) {
		case WHITE:
			white = MAX;
			break;
		case PURPLE:
			purple = MAX;
			break;
		}
	}

	public void on(Colour c, int brightness) {
		int b = normalise(brightness);
		switch (c) {
		case WHITE:
			white = b;
			break;
		case PURPLE:
			purple = b;
			break;
		}
	}

	public void off(Colour c) {
		switch (c) {
		case WHITE:
			white = MIN;
			break;
		case PURPLE:
			purple = MIN;
			break;
		}
	}

	@Override
	public String toString() {
		return "Light [white=" + white + ", purple=" + purple + "]";
	}
}
