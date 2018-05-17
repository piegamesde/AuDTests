package frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lab.HashTable;
import p.HashTable2;

public class TestGenerator {

	public static void main(String[] args) throws IOException {
		List<String> keys = IntStream.range(0, 1000).mapToObj(i -> randomKey(9)).collect(Collectors.toList());
		Files.write(Paths.get("TestFile3"), keys);
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "-1");
			generateHashes(keys, i, HashTable.HASH_FUNCTION_DIVISION);
			System.out.println(i + "-2");
			generateHashes(keys, i, HashTable.HASH_FUNCTION_FOLDING);
			System.out.println(i + "-3");
			generateHashes(keys, i, HashTable.HASH_FUNCTION_MIDSQUARE);
		}
	}

	private static void generateHashes(List<String> keys, int keyLength, String algorithm) throws IOException {
		HashTable2 table = new HashTable2((int) Math.pow(10, keyLength) * 4, algorithm, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
		Files.write(Paths.get("ResultFile3-" + algorithm + "-" + keyLength), keys.stream().map(table::getHash).map(String::valueOf).collect(Collectors.toList()));
	}

	private static String randomKey(int length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String ret = "";
		for (int i = 0; i < length; i++)
			ret += chars.charAt((int) (Math.random() * chars.length()));
		return ret;
	}
}