package frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lab.Navigation;

public class AllTests {

	private enum OutputFormat {
		Distance, Time
	};

	@Nested
	@DisplayName("Dijkstra TestFile1")
	class TestFile1 {

		@Test
		@DisplayName("Test Distance from A to C")
		public final void testFile1_A_C_Distance() {
			assertEquals(8, testDistance("TestFile1", "A", "C"), "From A to C: ");
		}

		@Test
		@DisplayName("Test Distance from A to G")
		public final void testFile1_A_G_Distance() {
			assertEquals(24, testDistance("TestFile1", "A", "G"), "From A to G: ");
		}

		@Test
		@DisplayName("Test Time from A to D")
		public final void testFile1_A_D_Time() {
			assertEquals(14, testTime("TestFile1", "A", "D"), "From A to D: ");
		}

		@Test
		@DisplayName("Test Time from A to F")
		public final void testFile1_A_F_Time() {
			assertEquals(18, testTime("TestFile1", "A", "F"), "From A to F: ");
		}

		@Test
		@DisplayName("Test Shortest Route from A to E")
		public final void testfile1_Route_A_E_Distance() {
			ArrayList<String> route = new ArrayList<String>();
			Navigation nav = new Navigation("TestFile1");
			// Build the route we expect to find
			route.add("A\\s*->\\s*C");
			route.add("C\\s*->\\s*D");
			route.add("D\\s*->\\s*E");
			assertTrue(testRoute(nav.findShortestRoute("A", "E"), route), "Route not correct");
		}

		@Test
		@DisplayName("Test Fastest Route from A to F")
		public final void testfile1_Route_A_F_Time() {
			ArrayList<String> route = new ArrayList<String>();
			Navigation nav = new Navigation("TestFile1");
			// Build the route we expect to find
			route.add("A\\s*->\\s*C");
			route.add("C\\s*->\\s*D");
			route.add("D\\s*->\\s*F");
			assertTrue(testRoute(nav.findFastestRoute("A", "F"), route), "Route not correct");
		}

	}

	@Nested
	@DisplayName("Dijkstra TestFile2")
	class TestFile2 {

		@Test
		@DisplayName("Test Distance from A to C")
		public final void testFile2_A_C_Distance() {
			assertEquals(8, testDistance("TestFile2", "A", "C"), "From A to C: ");
		}

		@Test
		@DisplayName("Test Distance from A to D")
		public final void testFile2_A_D_Distance() {
			assertEquals(12, testDistance("TestFile2", "A", "D"), "From A to D: ");
		}

		@Test
		@DisplayName("Test Distance from A to F")
		public final void testFile2_A_F_Distance() {
			assertEquals(16, testDistance("TestFile2", "A", "F"), "From A to F: ");
		}

		@Test
		@DisplayName("Test Time from A to C")
		public final void testFile2_A_C_Time() {
			assertEquals(8, testTime("TestFile2", "A", "C"), "From A to C: ");
		}

		@Test
		@DisplayName("Test Time from A to E")
		public final void testFile2_A_E_Time() {
			assertEquals(15, testTime("TestFile2", "A", "E"), "From A to E: ");
		}

		@Test
		@DisplayName("Test Time from A to F")
		public final void testFile2_A_F_Time() {
			assertEquals(12, testTime("TestFile2", "A", "F"), "From A to F: ");
		}

		@Test
		@DisplayName("Test Number of Vertices on Shortest Route from A to B")
		public final void testFile2_Size() {
			Navigation nav = new Navigation("TestFile2");
			assertEquals(16, nav.findShortestRoute("A", "B").size(), "Number of entries in output map: ");
		}

		@Test
		@DisplayName("Test Missing vertex G")
		public final void testFile2_Negative() {
			Navigation nav = new Navigation("TestFile2");
			assertEquals(-2, nav.findShortestDistance("A", "G"), "Test non-existing vertex: ");
		}

		@Test
		@DisplayName("Test Shortest Route from A to E")
		public final void testfile2_Route_A_E_Distance() {
			ArrayList<String> route = new ArrayList<String>();
			Navigation nav = new Navigation("TestFile2");
			// Build the route we expect to find
			route.add("A\\s*->\\s*B");
			route.add("B\\s*->\\s*C");
			route.add("C\\s*->\\s*D");
			route.add("D\\s*->\\s*E");
			assertTrue(testRoute(nav.findShortestRoute("A", "E"), route), "Route not correct");
		}

		@Test
		@DisplayName("Test Fastest Route from A to E")
		public final void testfile2_Route_A_E_Time() {
			ArrayList<String> route = new ArrayList<>();
			Navigation nav = new Navigation("TestFile2");
			// Build the route we expect to find
			route.add("A\\s*->\\s*B");
			route.add("B\\s*->\\s*C");
			route.add("C\\s*->\\s*E");
			assertTrue(testRoute(nav.findFastestRoute("A", "E"), route), "Route not correct");
		}

	}

	/**
	 * This method returns the shortest distance from start to stop on the map
	 * stored in filename.
	 * 
	 * It also writes the output map to a file. The file name follows the following
	 * format:
	 * 
	 * output_[filename]_from[start]to[stop]Distance.txt
	 * 
	 * @param filename
	 *            The name of the file storing the map
	 * @param start
	 *            Source node
	 * @param stop
	 *            Destination node
	 * @return The shortest distance between start and stop in km
	 */
	private final int testDistance(String filename, String start, String stop) {
		Navigation nav = new Navigation(filename);
		ArrayList<String> returnMap = new ArrayList<String>();

		returnMap = nav.findShortestRoute(start, stop);
		writeGraphToFile(returnMap, filename, start, stop, OutputFormat.Distance);
		return nav.findShortestDistance(start, stop);
	}

	/**
	 * This method returns the fastest route from start to stop on the map stored in
	 * filename.
	 * 
	 * It also writes the output map to a file. The file name follows the following
	 * format:
	 * 
	 * output_[filename]_from[start]to[stop]Time.txt
	 * 
	 * @param filename
	 *            The name of the file storing the map
	 * @param start
	 *            Source node
	 * @param stop
	 *            Destination node
	 * @return Fastest route in minutes
	 */
	private final int testTime(String filename, String start, String stop) {
		Navigation nav = new Navigation(filename);
		ArrayList<String> returnMap = new ArrayList<String>();

		returnMap = nav.findFastestRoute(start, stop);
		writeGraphToFile(returnMap, filename, start, stop, OutputFormat.Time);
		return nav.findFastestTime(start, stop);
	}

	/**
	 * This method tests wether the edges contained in boldEdges are present and
	 * marked as bold in map
	 * 
	 * @param map
	 *            The map to check, in dot format
	 * @param boldEdges
	 *            The edges to find
	 * @return True if all edges in boldEdges are marked bold in map
	 */
	private final boolean testRoute(ArrayList<String> map, ArrayList<String> boldEdges) {
		boolean correct = true;
		int matches = 0;
		for (String edge : boldEdges) { // for all edges we're looking for
			for (String line : map) { // for all lines in the map
				if (line.matches(".*" + edge + ".*")) { // if the edge is there
					correct = correct && line.matches(".*bold.*"); // check if it is bold
					matches++; // Count the number of bold lines correctly found
				}
			}
		}
		// Check if we found all of them
		correct = correct && (matches == boldEdges.size());
		return correct;
	}

	/**
	 * This method writes a map to file
	 * 
	 * The format of the filename of the file created depends on the last four
	 * parameters:
	 * 
	 * if format = OutputFormat.Time: output_[filename]_from[start]to[stop]Time.txt
	 * if format = OutputFormat.Distance:
	 * output_[filename]_from[start]to[stop]Distance.txt
	 * 
	 * @param map
	 * @param filename
	 * @param start
	 * @param stop
	 * @param format
	 */
	public final void writeGraphToFile(ArrayList<String> map, String filename, String start, String stop,
			OutputFormat format) {
		try {
			String typeString = null;
			switch (format) {
			case Distance:
				typeString = "distance";
				break;
			case Time:
				typeString = "time";
				break;
			}

			FileWriter fw = new FileWriter("output_" + filename + "_from" + start + "to" + stop + typeString + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);

			for (String element : map) {
				bw.write(element);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
