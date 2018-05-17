package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import lab.HashTable;

@RunWith(value = Parameterized.class)
public class HashTests {

	@Parameter(value = 0)
	public String	algorithm;

	@Parameter(value = 1)
	public int		keyLength;

	@Parameters(name = "{index}: hash({0}, {1})")
	public static Collection<Object[]> data() {
		Collection<Object[]> ret = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			ret.add(new Object[] { HashTable.HASH_FUNCTION_DIVISION, i });
			ret.add(new Object[] { HashTable.HASH_FUNCTION_FOLDING, i });
			ret.add(new Object[] { HashTable.HASH_FUNCTION_MIDSQUARE, i });
		}
		return ret;
	}

	@Test
	public void hashTest() throws IOException {
		HashTable table = new HashTable((int) Math.pow(10, keyLength) * 4, algorithm, "linear_probing");
		List<String> keys = Files.readAllLines(Paths.get("TestFile3"));
		List<String> expected = Files.readAllLines(Paths.get("ResultFile3-" + algorithm + "-" + keyLength));
		List<String> real = keys.stream().map(table::hash).map(String::valueOf).collect(Collectors.toList());
		for (int i = 0; i < keys.size(); i++)
			assertEquals(expected.get(i), real.get(i), "Line " + (i + 1) + ", key " + keys.get(i) + " is wrong with " + algorithm + ", key length " + keyLength);
	}
}
