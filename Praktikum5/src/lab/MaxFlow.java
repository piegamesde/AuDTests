package lab;

import java.util.ArrayList;
/**
 * MaxFlow.java
 */


public class MaxFlow {
	/**
	 * Return codes:
	 * 		-1 no source on the map
	 * 		-2 no destination on the map
	 * 		-3 if both source and destination points are not on the map
	 * 		-4 if no path can be found between source and destination
	 * 	MAXINT if sources identical to destinations
	 */
	public static final int NO_SOURCE_FOUND = -1;
	public static final int NO_DESTINATION_FOUND = -2;
	public static final int NO_SOURCE_DESTINATION_FOUND = -3;
	public static final int NO_PATH = -4;
	public static final int SOURCES_SAME_AS_DESTINATIONS = Integer.MAX_VALUE;
	
	/**
	 * The constructor, setting the name of the file to parse.
	 * 
	 * @param filename the absolute or relative path and filename of the file
	 */
	public MaxFlow(final String filename) {
		//TODO Add you code here
		
	}
	
	/**
	 * Calculates the maximum number of cars able to travel the graph specified
	 * in filename.
	 *
	 * @param sources a list of all source nodes
	 * @param destinations a list of all destination nodes
	 * @return 	the maximum number of cars able to travel the graph,
	 * 			NO_SOURCE_FOUND if no source is on the map
	 * 			NO_DESTINATION_FOUND if no destination is on the map
	 * 			NO_SOURCE_DESTINATION_FOUND if both - no source and no destination - are not on the map
	 * 			NO_PATH if no path can be found
	 * 			SOURCES_SAME_AS_DESTINATIONS if sources == destinations 
	 */
	public final int findMaxFlow(final String[] sources, final String[] destinations) {
		//TODO Add you code here
		return 0; // dummy, replace
	}
	
	/**
	 * Calculates the graph showing the maxFlow.
	 *
	 * @param sources a list of all source nodes
	 * @param destinations a list of all destination nodes
	 * @return a ArrayList of Strings as specified in the task in dot code
	 */
	public final ArrayList<String> findResidualNetwork(final String[] sources,	final String[] destinations) {
		//TODO Add you code here
		return null; // dummy, replace
	}

}