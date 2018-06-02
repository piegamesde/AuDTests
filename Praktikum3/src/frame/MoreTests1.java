package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import lab.B_Tree;

public class MoreTests1 {

	@Test
	public void simpleDotTest() throws IOException {
		B_Tree tree = new B_Tree(3);
		int size = 0;
		for (int i : new int[] { 11, 18, 43, 47, 42, 31, 55, 62, 71, 77, 83, 91, 99 }) {
			assertEquals(size++, tree.getB_TreeSize());
			tree.insert(new Entry("" + i, "", "OK"));
		}
		// assertLinesMatch(Files.readAllLines(Paths.get("data/TestFile3.txt")), tree.getB_Tree());
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile3.txt")), tree.getB_Tree());
	}

	// This test is WIP and won't do anything
	@Test
	public void simpleInsertDeleteTest() throws NumberFormatException, IOException {
		// Folie 49
		// .............. "P"
		// ..... "CL", ............. "TX"
		// "AB", "DEJK", "NO"; "QRS", "UV", "YZ"

		final boolean generate = false;

		int index = 0;
		Map<Integer, LinkedList<String>> lines = new HashMap<>();
		if (!generate) {
			for (String s : Files.readAllLines(Paths.get("data/TestFile4.txt"))) {
				int i = Integer.parseInt(s.split("-")[0]);
				if (!lines.containsKey(i))
					lines.put(i, new LinkedList<>());
				lines.get(i).add(s.substring(s.indexOf('-') + 1));
			}
		}

		final String insertChars = "ABCDELNOJKPQRTUVXYZWS";

		B_Tree tree = new B_Tree(3);
		for (char s : insertChars.toCharArray()) {
			assertNotNull(tree.insert(new Entry("" + s, "", "OK")));
			if (generate)
				for (String line : tree.getB_Tree())
					System.out.println(index + "-" +  line);
			else {
				Util.assertAscending(tree.getInorderTraversal());
				Util.assertTreeEquals(lines.get(index), tree.getB_Tree());
			}
			index++;
		}

		Util.assertAscending(tree.getInorderTraversal());

		for (char s : "DFCVZYONKLAR".toCharArray()) {
			if (insertChars.contains("" + s))
				assertNotNull(tree.delete("" + s));
			else
				assertNull(tree.delete("" + s));
			if (generate)
				for (String line : tree.getB_Tree())
					System.out.println(index +"-" +  line);
			else {
				Util.assertAscending(tree.getInorderTraversal());
				Util.assertTreeEquals(lines.get(index), tree.getB_Tree());
			}
			index++;
		}
	}

	/** Each bug a test. This may fail getting the size of the tree at the end */
	@Test
	public void simpleInsertDeleteTest2() throws IOException {
		B_Tree tree = new B_Tree(2);
		for (String s : Files.readAllLines(Paths.get("data/TestFile5.txt"))) {
			tree.getB_TreeSize();
			if (s.startsWith("+"))
				tree.insert(new Entry(s.substring(1)));
			else
				tree.delete(s.substring(1));
		}
		tree.getB_TreeSize();
	}

	/**
	 * Thanks to Roman
	 *
	 * @throws IOException
	 */
	@Test
	void simpleInsertDeleteTest3() throws IOException {
		B_Tree tree = new B_Tree(2);
		int count = 15;
		for (int i = 0; i < count; i++) {
			int num = 100 + i;
			String key = String.valueOf(num);
			assertTrue(tree.insert(new Entry(key, "", "")));
		}
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile6-1.txt")), tree.getB_Tree());
		assertNotNull(tree.delete("103"));
		assertEquals(tree.getB_TreeSize(), count - 1);
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile6-2.txt")), tree.getB_Tree());
	}

	@Test
	public void testSizeHeight() {
		B_Tree tree = new B_Tree(2);
		Random random = new Random(897465120L);
		Set<Entry> entries = new HashSet<Entry>();
		for (int i = 0; i < 10; i++) {
			Entry entry = Util.getRandomEntry(random);
			entries.add(entry);
			tree.insert(entry);
			assertEquals(entries.size(), tree.getB_TreeSize());
			Util.assertHeight(tree.getB_TreeHeight(), tree.getB_TreeSize(), 2);
		}
		int size = entries.size();
		for (Entry e : entries) {
			tree.delete(e.getKey());
			assertEquals(--size, tree.getB_TreeSize());
			Util.assertHeight(tree.getB_TreeHeight(), tree.getB_TreeSize(), 2);
		}
	}

	@Test
	void edgeCaseSplit() throws IOException {
		B_Tree tree = new B_Tree(2);
		for (char s : "ABCDE".toCharArray()) {
			assertTrue(tree.insert(new Entry("", "" + s, "")));
		}
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile7.txt")), tree.getB_Tree());
		assertFalse(tree.insert(new Entry("", "E", "")));
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile7.txt")), tree.getB_Tree());
	}
}