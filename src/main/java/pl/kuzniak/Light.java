package pl.kuzniak;

public class Light {
	public static final int MIN = 0;
	public static final int MAX = 100;

	private long white;
	private long purple;

	public Light() {
		this(MIN, MIN);
	}

	public Light(Light l) {
		this(l.white, l.purple);
	}

	public Light(long white, long purple) {
		this.white = white;
		this.purple = purple;
	}

	private static long normalise(long brightness) {
		return Math.min(MAX, Math.max(MIN, brightness));
	}

	public void normalise() {
		white = normalise(white);
		purple = normalise(purple);
	}

	// arithmetics

	public Light add(Light l) {
		return new Light(white + l.white, purple + l.purple);
	}

	public Light sub(Light l) {
		return new Light(white - l.white, purple - l.purple);
	}

	public Light mul(long x) {
		return new Light(white * x, purple * x);
	}

	public Light div(long x) {
		return new Light(white / x, purple / x);
	}

	// state modifiers

	@Deprecated
	public void setLight(Light l) {
		white = l.white;
		purple = l.purple;
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

	public int getWhite() {
		return (int) white;
	}

	public int getPurple() {
		return (int) purple;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(white).hashCode() + Long.valueOf(purple).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Light) {
			Light l = (Light) obj;
			return ((white == l.white) && (purple == l.purple));
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public String toString() {
		return "Light [white=" + white + ", purple=" + purple + "]";
	}
}
