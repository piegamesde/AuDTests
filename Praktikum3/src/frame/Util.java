package frame;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.hamcrest.CoreMatchers;
import lab.B_Tree;

public class Util {

	public static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static String getRandomKey(Random random, int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++)
			ret.append(CHARS[random.nextInt(36)]);
		return ret.toString();
	}

	public static Entry getRandomEntry(Random random) {
		return new Entry(getRandomKey(random, 5), getRandomKey(random, 4), random.nextBoolean() ? "OK" : "ERROR");
	}

	public static void assertAscending(List<Entry> entries) {
		for (int i = 0; i < entries.size() - 1; i++)
			assertTrue(entries.get(i).compareTo(entries.get(i + 1)) < 0,
					"Entry " + i + " is larger than its successor: " + entries.get(i) + ">" + entries.get(i + 1)
							+ (entries.size() < 100 ? " in " + entries.toString() : ""));
	}

	public static void printTree(B_Tree tree) {
		tree.getB_Tree().forEach(System.out::println);
		System.out.println();
	}

	public static void assertTreeEquals(List<String> expected, List<String> actual) {
		assertDotWellFormed(actual);
		if (expected.size() != actual.size())
			assertLinesMatch(expected, actual);
		assertNodesEqual("", buildNodeFromDot(expected), buildNodeFromDot(actual));
	}

	static void assertDotWellFormed(List<String> lines) {
		assertEquals("digraph {", lines.get(0), "Line 1 is wrong. The space before the bracket is required in this test");
		assertEquals("node[shape=record];", lines.get(1), "Line 2 is wrong");
		assertEquals("}", lines.get(lines.size() - 1), "Last line is wrong");
		for (int i = 2; i < lines.size() - 1; i++) {
			String line = lines.get(i);
			assertEquals(";", "" + line.charAt(line.length() - 1), "All lines must end with \";\" -- including line " + (i + 1));
			if (line.contains("->")) {
				assertTrue(line.contains(":f"));
				// TODO
			} else if (line.contains("[label=\"")) {
				// TODO
			} else if (!line.isEmpty())
				fail("All lines must either contain \"->\" or \"[label=\"\", which is not the case in line " + (i + 1) + ": " + line);
		}
	}

	static void assertNodesEqual(String parent, Node expected, Node actual) {
		assertThat("Child nodes from " + parent + " do not have matching data", actual.data, CoreMatchers.is(expected.data));
		assertThat("Child nodes from" + parent + " do not have the same children", actual.childNodes(), CoreMatchers.is(expected.childNodes()));

		expected.children.forEach(
				(i, s) -> assertNodesEqual(parent + " -> " + s.name, s, actual.children.get(i)));
	}

	static Node buildNodeFromDot(List<String> lines) {
		Map<String, Node> nodes = new HashMap<>();
		for (int i = 2; i < lines.size() - 1; i++) {
			String line = lines.get(i);
			if (line.contains("[label=\"")) {
				String name = line.substring(0, line.indexOf('['));
				nodes.put(name, new Node(name, (line.split("\""))[1].split("\\|?+<f[0-9]+>\\*?+\\|?+")));
			}
		}
		for (int j = 2; j < lines.size() - 1; j++) {
			String line = lines.get(j);
			if (!line.contains("[label=\"")) {
				int i = line.indexOf(':');
				String name = line.substring(0, i);
				String data = line.split("->")[1];
				nodes.get(name).children.put(
						Integer.parseInt(line.substring(i + 2, i + 3)),
						nodes.get(data.substring(0, data.length() - 1)));
			}
		}
		if (!nodes.containsKey("root"))
			fail("A root node is required in each dot file, but none was found");
		return nodes.get("root");
	}

	static class Node {

		String				name;
		Map<Integer, Node>	children;
		List<String>		data;

		Node(String name, String... data) {
			this.name = name;
			this.children = new HashMap<>();
			this.data = Arrays.asList(data);
		}

		public List<String> childNodes() {
			return children.entrySet().stream().map(e -> "f" + e.getKey() + "->" + e.getValue().name).collect(Collectors.toList());
		}
	}

	public static void assertHeight(int height, int size, int t) {
		if (size == 0) {
			assertEquals(0, height, "An empty tree should have height 0");
			return;
		}

		// height <= floor(log_t((size+1)/2)
		double max = Math.floor(Math.log((size + 1) / 2.0) / Math.log(t));
		// height >= ceil(log_2t(size+1))-1
		double min = Math.ceil(Math.log(size + 1) / Math.log(2 * t)) - 1;

		assertTrue(height <= max, "Tree is too tall: Should be at most " + max + " for " + size + " elements with t=" + t + ", but was " + height);
		assertTrue(height >= min, "Tree is too small: Should be at least " + max + " for " + size + " elements with t=" + t + ", but was " + height);
	}
}