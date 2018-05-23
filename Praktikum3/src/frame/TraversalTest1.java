package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import lab.B_Tree;

@RunWith(value = Parameterized.class)
public class TraversalTest1 {

	// WARNING: THIS TEST WILL TAKE A WHILE. COMMENT OUT SOME OF THESE NUMBERS TO ACCELERATE THE PROCESS

	public static final int[][]	SIZES	= { { 10, 1 }, { 100, 1 }, { 1000, 1 }, { 10, 2 }, { 100, 2 }, { 1000, 2 },
			{ 10, 3 }, { 100, 4 }, { 10, 10 }, { 10, 100 }, { 100, 100 }, { 10000, 20 }, { 100000, 1000 }, { 10000, 10000 },
			{ 100, 101 }, { 100, 99 } };
	public static final long[]	SEEDS	= {
			0, 65843210, "Hello World".hashCode(), System.nanoTime(), "Karsten ist der Beste".hashCode(),
			123456789, "Hoch lebe das Praktikum".length(), System.getenv().toString().hashCode() };

	@Parameter(value = 0)
	public int					size;

	@Parameter(value = 1)
	public int					degree;

	@Parameter(value = 2)
	public long					seed;

	@Parameters(name = "size={0}, degree={1}, seed={2}")
	public static Collection<Object[]> data() {
		Collection<Object[]> ret = new ArrayList<>();
		for (int[] size : SIZES)
			for (long seed : SEEDS)
				ret.add(new Object[] { size[0], size[1], seed });
		return ret;
	}

	@Test
	public void testAscendingTraversal() {
		Random random = new Random(seed);
		Set<Entry> entries = new TreeSet<>();
		B_Tree tree = new B_Tree(degree);
		for (int i = 0; i < size; i++) {
			Entry entry = Util.getRandomEntry(random);
			if (entries.contains(entry))
				continue;
			entries.add(entry);
			tree.insert(entry);
		}
		/* This compares the sorted list with the traversal, which is much slower due to sorting of 100000 random elements */
		// entries.sort(Comparator.naturalOrder());
		// assertThat(tree.getInorderTraversal(), is(entries));

		/* Instead, we only look at the size and if the list is ascending */
		List<Entry> result = tree.getInorderTraversal();
		assertEquals(size, result.size(), size + " entries got inserted, but only " + result.size() + " got traversed");
		for (int i = 0; i < size - 1; i++)
			assertTrue(result.get(i).compareTo(result.get(i + 1)) < 0, "Entry " + i + " is larger than its successor: " + result.get(i) + ">" + result.get(i + 1));
	}
}
