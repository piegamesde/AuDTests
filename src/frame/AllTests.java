package frame;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lab.HashTable;

@DisplayName("HashTable tests")
public class AllTests {

	protected int[] getAdresses(ArrayList<String> table, String key) {

		for (String line : table) {
			if (line.matches(".*" + key + ".*")) {
				String[] lastPart = line.split("\\x7C");
				if (lastPart.length == 3) {
					String allNumbersAndEnd = lastPart[line.split("\\x7C").length - 1].substring(0,
							lastPart[line.split("\\x7C").length - 1].indexOf("}"));
					String[] allNumbers = allNumbersAndEnd.split(",");
					int[] numbers = new int[allNumbers.length];
					for (int i = 0; i < allNumbers.length; i++) {
						numbers[i] = Integer.valueOf(allNumbers[i].trim());
					}
					return numbers;
				}
			}
		}

		return null;
	}

	protected void printArrayList(ArrayList<String> dot) {
		try {
			FileWriter fw = new FileWriter("test.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			for (String string : dot) {
				bw.write(string + System.getProperty("line.separator"));
			}

			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(Division, Linear)")
	class HashTableDivisionLinear {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_DivisionLinear(TestReporter reporter) {
			HashTable table = new HashTable(10, "division", "linear_probing");
			int loaded = table.loadFromFile("TestFile1");
			assertTrue(loaded == 19, "Didn't load 19 entries from TestFile1 with division and linear_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_DivisionLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "division", "linear_probing");
			assertTrue(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Find")
		public void testFind_DivisionLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");
			HashTable table = new HashTable(10, "division", "linear_probing");

			boolean inserted = table.insert(testEntry1);
			assertTrue(inserted);
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_DivisionLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "division", "linear_probing");

			boolean inserted = table.insert(testEntry1);
			assertTrue(inserted);

			Entry deletedEntry = table.delete(testEntry1.getKey());
			assertNotNull(deletedEntry);

			Entry e = table.find(testEntry1.getKey());
			assertNull(e);
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_DivisionLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "division", "linear_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_DivisionLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			Entry testEntry2 = new Entry();
			testEntry2.setKey("X8IG4LDXS");
			testEntry2.setData("OK");

			HashTable table = new HashTable(10, "division", "linear_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());
			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 2, testEntry2.getKey() + " should have home adress 2.");
		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(Division, Quadratic)")
	class HashTableDivisionQuadratic {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_DivisionQuadratic() {
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			assertTrue(table.loadFromFile("TestFile1") == 19,
					"Didn't load 19 entries from TestFile1 with division and quadratic_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_DivisionQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_DivisionQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			assertNotNull(table.delete(testEntry1.getKey()));
			assertNull(table.find(testEntry1.getKey()));
		}

		@Test
		@DisplayName("Find")
		public void testFind_DivisionQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_DivisionQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_DivisionQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");
			Entry testEntry2 = new Entry();
			testEntry2.setKey("X8IG4LDXS");
			testEntry2.setData("OK");
			HashTable table = new HashTable(10, "division", "quadratic_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());
			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 2, testEntry2.getKey() + " should have home adress 2.");
		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(MidSquare, Linear)")
	class HashTableMidSquareLinear {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_MidSquareLinear() {
			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			assertTrue(table.loadFromFile("TestFile1") == 19,
					"Didn't load 19 entries from TestFile1 with mid_square and linear_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_MidSquareLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			assertNotNull(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Find")
		public void testFind_MidSquareLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			assertNotNull(table.insert(testEntry1));
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_MidSquareLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			assertNotNull(table.insert(testEntry1));
			assertNotNull(table.delete(testEntry1.getKey()));
			assertNull(table.find(testEntry1.getKey()));
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_MidSquareLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_MidSquareLinear() {

			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");

			testEntry1.setData("OK");

			Entry testEntry2 = new Entry();
			testEntry2.setKey("F5HF8MECA");
			testEntry2.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "linear_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());
			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 0, testEntry2.getKey() + " should have home adress 3.");

		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(MidSquare, Quadratic)")
	class HashTableMidSquareQuadratic {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_MidSquareQuadractic() {
			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			assertTrue(table.loadFromFile("TestFile1") == 19,
					"Didn't load 19 entries from TestFile1 with mid_square and quadratic_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_MidSquareQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Find")
		public void testFind_MidSquareQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_MidSquareQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			assertNotNull(table.delete(testEntry1.getKey()));
			assertNull(table.find(testEntry1.getKey()));
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_MidSquareQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_MidSquareQuadratic() {

			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");

			testEntry1.setData("OK");

			Entry testEntry2 = new Entry();
			testEntry2.setKey("F5HF8MECA");
			testEntry2.setData("OK");

			HashTable table = new HashTable(10, "mid_square", "quadratic_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());
			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 0, testEntry2.getKey() + " should have home adress 0.");
		}

	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(Folding, Linear)")
	class HashTableFoldingLinear {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_FoldingLinear() {
			HashTable table = new HashTable(10, "folding", "linear_probing");
			assertTrue(table.loadFromFile("TestFile1") == 19,
					"Didn't load 19 entries from TestFile1 with folding and linear_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_FoldingLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "linear_probing");
			assertNotNull(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Find")
		public void testFind_FoldingLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "linear_probing");
			assertNotNull(table.insert(testEntry1));
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_FoldingLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "linear_probing");
			assertNotNull(table.insert(testEntry1));
			assertNotNull(table.delete(testEntry1.getKey()));
			assertNull(table.find(testEntry1.getKey()));
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_FoldingLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "linear_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_FoldingLinear() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			Entry testEntry2 = new Entry();
			testEntry2.setKey("Z7IG5LDXS");
			testEntry2.setData("OK");

			HashTable table = new HashTable(10, "folding", "linear_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());

			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 5, testEntry2.getKey() + " should have home adress 5.");

		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("HashTable(Folding, Quadratic)")
	class HashTableFoldingQuadratic {

		@Test
		@DisplayName("Read test file")
		public void testReadTestFile1_FoldingQuadratic() {
			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			assertTrue(table.loadFromFile("TestFile1") == 19,
					"Didn't load 19 entries from TestFile1 with folding and quadratic_probing.");
		}

		@Test
		@DisplayName("Insert")
		public void testInsert_FoldingQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
		}

		@Test
		@DisplayName("Find")
		public void testFind_FoldingQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			Entry foundEntry = table.find(testEntry1.getKey());
			assertNotNull(foundEntry, "Didn't find Entry " + testEntry1.getKey() + ".");
			assertTrue(foundEntry == testEntry1, "Inserted Entry " + testEntry1.getKey() + " and found Entry "
					+ foundEntry.getKey() + " are not the same.");
		}

		@Test
		@DisplayName("Delete")
		public void testDelete_FoldingQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("ABCDELDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			assertNotNull(table.insert(testEntry1));
			assertNotNull(table.delete(testEntry1.getKey()));
			assertNull(table.find(testEntry1.getKey()));
		}

		@Test
		@DisplayName("Home address")
		public void testHomeAdress_FoldingQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			table.insert(testEntry1);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry1.getKey());
			assertTrue(adresses == null, testEntry1.getKey() + " is not at home adress.");
		}

		@Test
		@DisplayName("Collision")
		public void testCollisionAdress_FoldingQuadratic() {
			Entry testEntry1 = new Entry();
			testEntry1.setKey("Z8IG4LDXS");
			testEntry1.setData("OK");

			Entry testEntry2 = new Entry();
			testEntry2.setKey("Z7IG5LDXS");
			testEntry2.setData("OK");

			HashTable table = new HashTable(10, "folding", "quadratic_probing");
			table.insert(testEntry1);
			table.insert(testEntry2);
			ArrayList<String> tableArrayList = table.getHashTable();
			int[] adresses = getAdresses(tableArrayList, testEntry2.getKey());
			assertTrue(adresses != null, testEntry2.getKey() + " is at home adress.");
			assertTrue(adresses.length == 1 && adresses[0] == 5, testEntry2.getKey() + " should have home adress 5.");
		}
	}

}