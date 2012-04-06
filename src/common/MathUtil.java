package common;

public class MathUtil {

	public static float clamp(float n, float min, float max) {
		return n > max ? max : n < min ? min : n;
	}

	public static float modbase(float n, float wrap) {
		if (n < 0)
			n += wrap;
		if (n > wrap)
			n %= wrap;
		return n;
	}

}
