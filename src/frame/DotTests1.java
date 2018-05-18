package frame;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;
import lab.HashTable;

public class DotTests1 {

	/** Tests the .dot file from the PDF */
	@Test
	public void testDotFile() throws IOException {
		HashTable table = new HashTable(11, "division", "linear_probing");
		table.loadFromFile("data/TestFile2");
		List<String> lines = table.getHashTable();
		List<String> expected = Files.readAllLines(Paths.get("data/ResultFile2"));
		assertLinesMatch(expected, lines);
	}
}