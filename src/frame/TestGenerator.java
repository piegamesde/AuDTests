package frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lab.HashTable;
import p.HashTable3;

public class TestGenerator {

	public static void main(String[] args) throws IOException {
		// if (true)
		// return;
		// ;
		{
			System.out.println("Hashes");
			List<String> keys = Files.readAllLines(Paths.get("TestFile3"));
			// List<String> keys = IntStream.range(0, 10000).mapToObj(i -> randomKey(9)).collect(Collectors.toList());
			// Files.write(Paths.get("TestFile3"), keys);
			for (int i = 0; i < 5; i++) {
				System.out.println(i + "-1");
				generateHashes(keys, i, HashTable.HASH_FUNCTION_DIVISION);
				System.out.println(i + "-2");
				generateHashes(keys, i, HashTable.HASH_FUNCTION_FOLDING);
				System.out.println(i + "-3");
				generateHashes(keys, i, HashTable.HASH_FUNCTION_MIDSQUARE);
			}
		}
		{
			System.out.println("Dot files");
			List<String> keys = Files.readAllLines(Paths.get("TestFile4"));
			// List<String> keys = IntStream.range(0, 10).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			// Files.write(Paths.get("TestFile4"), keys);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
		}
		{
			System.out.println("Dot files");
			List<String> keys = Files.readAllLines(Paths.get("TestFile5"));
			// List<String> keys = IntStream.range(0, 10000).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			// Files.write(Paths.get("TestFile5"), keys);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 5, HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING);
		}
	}

	private static void generateHashes(List<String> keys, int keyLength, String algorithm) throws IOException {
		HashTable3 table = new HashTable3((int) Math.pow(10, keyLength) * 4, algorithm, HashTable.COLLISION_RESOLUTION_LINEARPROBING);
		Files.write(Paths.get("ResultFile3-" + algorithm + "-" + keyLength), keys.stream().map(table::hash).map(String::valueOf).collect(Collectors.toList()));
	}

	private static void generateDot(List<String> keys, int id, String algorithm, String probing) throws IOException {
		HashTable3 table = new HashTable3(10, algorithm, probing);
		table.loadFromFile("TestFile" + id);
		Files.write(Paths.get("ResultFile" + id + "-" + algorithm + "-" + probing + ".dot"), table.getHashTable());
	}

	private static String randomKey(int length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String ret = "";
		for (int i = 0; i < length; i++)
			ret += chars.charAt((int) (Math.random() * chars.length()));
		return ret;
	}
}