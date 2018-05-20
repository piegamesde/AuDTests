package frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lab.Constants;
import lab.HashTable;

public class TestGenerator {

	public static void main(String[] args) throws IOException {
		generateZeroHashes();
		// generateInsertTestFiles();
		// generateTestFiles();
	}

	private static void generateInsertTestFiles() throws IOException {
		{
			System.out.println("Insert files files");
			// List<String> keys = Files.readAllLines(Paths.get("data/TestFile7"));
			List<String> keys = IntStream.range(0, 10000).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			Files.write(Paths.get("data/TestFile7"), keys);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateProgressiveDot(keys, 7, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
		}
	}

	private static void generateZeroHashes() {
		String data = "return Arrays.asList(new Object[][] {\n";
		for (int i : HashTests2.CAPACITIES) {
			data += "{ Constants.HASH_FUNCTION_FOLDING, " + i + ", \"" + generateHash(i, Constants.HASH_FUNCTION_FOLDING) + "\" },\n";
			data += "{ Constants.HASH_FUNCTION_MIDSQUARE, " + i + ", \"" + generateHash(i, Constants.HASH_FUNCTION_MIDSQUARE) + "\" },\n";
		}
		data = data.substring(0, data.length() - 2);
		data += "\n});";
		System.out.println(data);
	}

	private static String generateHash(int capacity, String algorithm) {
		HashTable table = new HashTable(capacity, algorithm, Constants.COLLISION_RESOLUTION_LINEARPROBING);

		System.out.println(capacity);
		while (true) {
			String hash = randomKey(5);
			int value = table.hash(hash);
			// System.out.println(capacity + " " + hash + " " + value);
			if (value != 0 && (value % capacity) == 0) {
				System.out.println(capacity + " " + hash + " " + value);
				return hash + randomKey(9);
			}
		}
	}

	private static void generateTestFiles() throws IOException {
		{
			System.out.println("Hashes");
			List<String> keys = Files.readAllLines(Paths.get("data/TestFile3"));
			// List<String> keys = IntStream.range(0, 10000).mapToObj(i -> randomKey(9)).collect(Collectors.toList());
			// Files.write(Paths.get("TestFile3"), keys);
			for (int i = 0; i < 5; i++) {
				System.out.println(i + "-1");
				generateHashes(keys, i, Constants.HASH_FUNCTION_DIVISION);
				System.out.println(i + "-2");
				generateHashes(keys, i, Constants.HASH_FUNCTION_FOLDING);
				System.out.println(i + "-3");
				generateHashes(keys, i, Constants.HASH_FUNCTION_MIDSQUARE);
			}
		}
		{
			System.out.println("Dot files");
			List<String> keys = Files.readAllLines(Paths.get("data/TestFile4"));
			// List<String> keys = IntStream.range(0, 10).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			// Files.write(Paths.get("data/TestFile4"), keys);
			generateDot(keys, 4, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 4, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			generateDot(keys, 4, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			generateDot(keys, 4, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
		}
		{
			System.out.println("Dot files");
			List<String> keys = Files.readAllLines(Paths.get("data/TestFile5"));
			// List<String> keys = IntStream.range(0, 1000).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			// Files.write(Paths.get("data/TestFile5"), keys);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 5, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
		}
		{
			System.out.println("Dot files");
			List<String> keys = Files.readAllLines(Paths.get("data/TestFile6"));
			// List<String> keys = IntStream.range(0, 10000).mapToObj(i -> randomKey(5) + ";" + randomKey(4) + ";" + randomKey(2)).collect(Collectors.toList());
			// Files.write(Paths.get("data/TestFile6"), keys);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_LINEARPROBING);
			System.out.println("a");
			generateDot(keys, 6, Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_QUADRATICPROBING);
		}

	}

	private static void generateHashes(List<String> keys, int keyLength, String algorithm) throws IOException {
		HashTable table = new HashTable((int) Math.pow(10, keyLength) * 4, algorithm, Constants.COLLISION_RESOLUTION_LINEARPROBING);
		Files.write(Paths.get("data/ResultFile3-" + algorithm + "-" + keyLength), keys.stream().map(table::hash).map(String::valueOf).collect(Collectors.toList()));
	}

	private static void generateDot(List<String> keys, int id, String algorithm, String probing) throws IOException {
		HashTable table = new HashTable(10, algorithm, probing);
		table.loadFromFile("data/TestFile" + id);
		Files.write(Paths.get("data/ResultFile" + id + "-" + algorithm + "-" + probing + ".dot"), table.getHashTable());
	}

	private static void generateProgressiveDot(List<String> keys, int id, String algorithm, String probing) throws IOException {
		HashTable table = new HashTable(10, algorithm, probing);
		int i = 0;
		Set<String> lines = new HashSet<>();
		for (String key : keys) {
			if (i % 500 == 0)
				System.out.println(
						lines.size());
			Entry entry = new Entry();
			entry.setKey(key);
			entry.setData("NO DATA");
			table.insert(entry);
			// Files.write(Paths.get("data/insert/ResultFile" + id + "-" + algorithm + "-" + probing + "-step" + i + ".dot"), table.getHashTable());
			i++;
			lines.addAll(table.getHashTable());
		}
		System.out.println(lines.size());
	}

	private static String randomKey(int length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String ret = "";
		for (int i = 0; i < length; i++)
			ret += chars.charAt((int) (Math.random() * chars.length()));
		return ret;
	}
}