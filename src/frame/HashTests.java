package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import lab.HashTable;

public class HashTests {

	@Test
	public void simpleHashTest() {
		HashTable table = new HashTable(800, HashTable.HASH_FUNCTION_FOLDING, "linear_probing");
		assertEquals(647, table.hash("Z8IG4LDXS"));
	}
}
