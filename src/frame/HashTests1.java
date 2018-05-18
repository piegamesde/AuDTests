package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import lab.Constants;
import lab.HashTable;

public class HashTests1 {

	@Test
	public void foldingTest1() {
		HashTable table = new HashTable(800, Constants.HASH_FUNCTION_FOLDING, "linear_probing");
		assertEquals(647, table.hash("Z8IG4LDXS"));
	}

	@Test
	public void foldingTest2() {
		Entry testEntry1 = new Entry();
		testEntry1.setKey("Z8IG4LDXS");
		testEntry1.setData("OK");

		Entry testEntry2 = new Entry();
		testEntry2.setKey("Z7IG5LDXS");
		testEntry2.setData("OK");

		HashTable table = new HashTable(10, "folding", "linear_probing");
		assertEquals(5, table.hash("Z8IG4LDXS"));
		assertEquals(5, table.hash("Z7IG5LDXS"));
	}
}
