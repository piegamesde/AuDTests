package frame;

import java.util.Random;

public class Util {

	public static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static String getRandomKey(Random random, int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++)
			ret.append(CHARS[random.nextInt(36)]);
		return ret.toString();
	}

	public static Entry getRandomEntry(Random random) {
		return new Entry(getRandomKey(random, 5), getRandomKey(random, 4), random.nextBoolean() ? "OK" : "ERROR");
	}
}