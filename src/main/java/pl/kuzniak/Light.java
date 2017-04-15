package pl.kuzniak;

public class Light {
	public static final int MIN = 0;
	public static final int MAX = 100;

	public int white;
	public int purple;

	private static int normalise(int brightness) {
		return Math.min(MAX, Math.max(MIN, brightness));
	}

	public void setWhite(int brightness) {
		white = normalise(brightness);
	}

	public void setPurple(int brightness) {
		purple = normalise(brightness);
	}

	public void on() {
		setWhite(MAX);
		setPurple(MAX);
	}

	public void off() {
		setWhite(MIN);
		setPurple(MIN);
	}

	@Override
	public String toString() {
		return "Light [white=" + white + ", purple=" + purple + "]";
	}
}
