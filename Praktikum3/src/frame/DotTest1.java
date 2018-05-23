package frame;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import lab.B_Tree;

public class DotTest1 {

	@Test
	public void simpleDotTest() throws IOException {
		B_Tree tree = new B_Tree(3);
		for (int i : new int[] { 11, 18, 43, 47, 42, 31, 55, 62, 71, 77, 83, 91, 99 })
			tree.insert(new Entry("" + i, "", "OK"));
		assertLinesMatch(Files.readAllLines(Paths.get("data/TestFile2.txt")), tree.getB_Tree());
	}
}