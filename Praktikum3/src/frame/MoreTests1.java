package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
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
		assertLinesMatch(Files.readAllLines(Paths.get("data/TestFile3.txt")), tree.getB_Tree());
		Util.assertTreeEquals(Files.readAllLines(Paths.get("data/TestFile3.txt")), tree.getB_Tree());
	}

	// This test is WIP and won't do anything
	@Test
	public void simpleInsertDeleteTest() {
		if (true)
			return;
		// Folie 49
		// .............. "P"
		// ..... "CL", ............. "TX"
		// "AB", "DEJK", "NO"; "QRS", "UV", "YZ"
		// --> "Delete D"
		B_Tree tree = new B_Tree(3);
		for (char s : "ABCDELNOJKPQRTUVXYZWS".toCharArray()) {
			// System.out.println("Insert " + s);
			tree.insert(new Entry("" + s, "", "OK"));
		}

		tree.getInorderTraversal();

		// assert tree equals
		// Util.assertTreeEquals(expected, tree.getB_Tree());

		tree.delete("D");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("F");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("C");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("V");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("Z");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("Y");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("O");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("N");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("K");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		tree.delete("L");
		Util.printTree(tree);
		Util.assertAscending(tree.getInorderTraversal());

		// assert tree equals
		// Util.assertTreeEquals(expected, tree.getB_Tree());
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

	@Test
	public void testSizeHeight() {
		B_Tree tree = new B_Tree(2);
		Random random = new Random(897465120L);
		Set<Entry> entries = new HashSet<Entry>();
		for (int i = 0; i < 10; i++) {
			Entry entry = Util.getRandomEntry(random);
			entries.add(entry);
			tree.insert(entry);
			assertEquals(i + 1, tree.getB_TreeSize());
			Util.assertHeight(tree.getB_TreeHeight(), tree.getB_TreeSize(), 2);
		}
		int size = entries.size();
		for (Entry e : entries) {
			tree.delete(e.getKey());
			assertEquals(--size, tree.getB_TreeSize());
			Util.assertHeight(tree.getB_TreeHeight(), tree.getB_TreeSize(), 2);
		}
	}
}