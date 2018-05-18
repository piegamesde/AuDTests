package frame;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
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
import lab.HashTable;

@RunWith(value = Parameterized.class)
public class DotTests2 {

	@Parameter(value = 0)
	public String	algorithm;

	@Parameter(value = 1)
	public String	probing;

	@Parameters(name = "{index}: dot({0}, {1})")
	public static Collection<Object[]> data() {
		Collection<Object[]> ret = new ArrayList<>();
		ret.add(new Object[] { HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { HashTable.HASH_FUNCTION_DIVISION, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING });
		ret.add(new Object[] { HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { HashTable.HASH_FUNCTION_FOLDING, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING });
		ret.add(new Object[] { HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_LINEARPROBING });
		ret.add(new Object[] { HashTable.HASH_FUNCTION_MIDSQUARE, HashTable.COLLISION_RESOLUTION_QUADRATICPROBING });
		return ret;
	}

	@Test
	public void hashTestSmall() throws IOException {
		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("ResultFile4-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("TestFile4");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}

	@Test
	public void hashTestMedium() throws IOException {
		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("ResultFile5-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("TestFile5");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}

	@Test
	public void hashTestLarge() throws IOException {
		HashTable table = new HashTable(10, algorithm, probing);
		List<String> expected = Files.readAllLines(Paths.get("ResultFile6-" + algorithm + "-" + probing + ".dot"));
		table.loadFromFile("TestFile6");
		List<String> real = table.getHashTable();
		assertLinesMatch(expected, real);
	}
}
