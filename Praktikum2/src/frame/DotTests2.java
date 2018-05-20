package frame;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import lab.Constants;
import lab.HashTable;

@RunWith(value = Parameterized.class)
public class DotTests2 {

	// Set one of these to false to disable the respective tests

	public static final boolean	TEST_SMALL		= true;
	public static final boolean	TEST_MEDIUM		= true;
	public static final boolean	TEST_LARGE		= true;

	public static final int		MAX_ITEM_COUNT	= 100;

	@Parameter(value = 0)
	public String				algorithm;

	@Parameter(value = 1)
	public String				probing;

	@Parameters(name = "{index}: dot({0}, {1})")
	public static Collection<Object[]> data() {
		Collection<Object[]> ret = new ArrayList<>();
		ret.add(new Object[] { Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { Constants.HASH_FUNCTION_DIVISION, Constants.COLLISION_RESOLUTION_QUADRATICPROBING });
		ret.add(new Object[] { Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { Constants.HASH_FUNCTION_FOLDING, Constants.COLLISION_RESOLUTION_QUADRATICPROBING });
		ret.add(new Object[] { Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { Constants.HASH_FUNCTION_MIDSQUARE, Constants.COLLISION_RESOLUTION_QUADRATICPROBING });
		return ret;
	}

	@Test
	public void hashTestSmall() throws IOException {
		assertTrue(TEST_SMALL, "Skipping small tests due to configuration");

		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("data/ResultFile4-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("data/TestFile4");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}

	@Test
	public void hashTestMedium() throws IOException {
		assertTrue(TEST_MEDIUM, "Skipping medium tests due to configuration");

		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("data/ResultFile5-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("data/TestFile5");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}

	@Test
	public void hashTestLarge() throws IOException {
		assertTrue(TEST_LARGE, "Skipping large tests due to configuration");

		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("data/ResultFile6-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("data/TestFile6");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}

	// @Test
	// public void hashTestProgressive() throws IOException {
	// assertTrue(MAX_ITEM_COUNT <= 10000, "The test does not have more than 10.000 elements");
	// assertTrue(MAX_ITEM_COUNT > 0, "Insert at least one element");
	//
	// HashTable table = new HashTable(10, algorithm, probing);
	// List<String> keys = Files.readAllLines(Paths.get("data/TestFile7"));
	// int i = 0;
	// for (String key : keys) {
	// Entry entry = new Entry();
	// entry.setKey(key);
	// entry.setData("NO DATA");
	// table.insert(entry);
	// // assertLinesMatch(
	// // Files.readAllLines(Paths.get("data/insert/ResultFile7" + "-" + algorithm + "-" + probing + "-step" + i + ".dot")),
	// // table.getHashTable());
	// i++;
	// }
	// }
}
