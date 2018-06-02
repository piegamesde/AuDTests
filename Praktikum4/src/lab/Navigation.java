package lab;

import java.util.ArrayList;

/**
 * The class Navigation finds the shortest (and/or) path between points on a map
 * using the Dijkstra algorithm
 */
public class Navigation {
	/**
	 * Return codes: -1 if the source is not on the map -2 if the destination is
	 * not on the map -3 if both source and destination points are not on the
	 * map -4 if no path can be found between source and destination
	 */

	public static final int SOURCE_NOT_FOUND = -1;
	public static final int DESTINATION_NOT_FOUND = -2;
	public static final int SOURCE_DESTINATION_NOT_FOUND = -3;
	public static final int NO_PATH = -4;

	/**
	 * The constructor takes a filename as input, it reads that file and fill
	 * the nodes and edges Lists with corresponding node and edge objects
	 * 
	 * @param filename
	 *            name of the file containing the input map
	 */
	public Navigation(String filename) {
		//TODO Add you code here

	}

	/**
	 * This methods finds the shortest route (distance) between points A and B
	 * on the map given in the constructor.
	 * 
	 * If a route is found the return value is an object of type
	 * ArrayList<String>, where every element is a String representing one line
	 * in the map. The output map is identical to the input map, apart from that
	 * all edges on the shortest route are marked "bold". It is also possible to
	 * output a map where all shortest paths starting in A are marked bold.
	 * 
	 * The order of the edges as they appear in the output may differ from the
	 * input.
	 * 
	 * @param A
	 *            Source
	 * @param B
	 *            Destinaton
	 * @return returns a map as described above if A or B is not on the map or
	 *         if there is no path between them the original map is to be
	 *         returned.
	 */
	public ArrayList<String> findShortestRoute(String A, String B) {
		//TODO  Add you code here
		
		return new ArrayList<>(); // dummy, replace
	}

	/**
	 * This methods finds the fastest route (in time) between points A and B on
	 * the map given in the constructor.
	 * 
	 * If a route is found the return value is an object of type
	 * ArrayList<String>, where every element is a String representing one line
	 * in the map. The output map is identical to the input map, apart from that
	 * all edges on the shortest route are marked "bold". It is also possible to
	 * output a map where all shortest paths starting in A are marked bold.
	 * 
	 * The order of the edges as they appear in the output may differ from the
	 * input.
	 * 
	 * @param A
	 *            Source
	 * @param B
	 *            Destinaton
	 * @return returns a map as described above if A or B is not on the map or
	 *         if there is no path between them the original map is to be
	 *         returned.
	 */
	public ArrayList<String> findFastestRoute(String A, String B) {
		//TODO Add you code here
		
		return new ArrayList<>(); // dummy, replace
	}

	/**
	 * Finds the shortest distance in kilometers between A and B using the
	 * Dijkstra algorithm.
	 * 
	 * @param A
	 *            the start point A
	 * @param B
	 *            the destination point B
	 * @return the shortest distance in kilometers rounded upwards.
	 *         SOURCE_NOT_FOUND if point A is not on the map
	 *         DESTINATION_NOT_FOUND if point B is not on the map
	 *         SOURCE_DESTINATION_NOT_FOUND if point A and point B are not on
	 *         the map NO_PATH if no path can be found between point A and point
	 *         B
	 */
	public int findShortestDistance(String A, String B) {
		//TODO Add you code here
		int sd = 0; 

		return sd;
	}

	/**
	 * Find the fastest route between A and B using the dijkstra algorithm.
	 * 
	 * @param A
	 *            Source
	 * @param B
	 *            Destination
	 * @return the fastest time in minutes rounded upwards. SOURCE_NOT_FOUND if
	 *         point A is not on the map DESTINATION_NOT_FOUND if point B is not
	 *         on the map SOURCE_DESTINATION_NOT_FOUND if point A and point B
	 *         are not on the map NO_PATH if no path can be found between point
	 *         A and point B
	 */
	public int findFastestTime(String pointA, String pointB) {
		//TODO Add you code here
		int ft = 0;

		return ft;
	}

}
