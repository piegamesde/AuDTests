package frame;
/**
 * DotFileConstants.java
 *
 * Version:				1.0
 */


/**
 * Holds constant Strings for formatting the output to the dot-format.
 * 
 */

public class DotFileConstants {
	
	//basic Strings
	
	/** String for the dot-file format indicating a graph. */
	public static final String DOT_FILE_DIGRAPH = "digraph";
	/** String for the dot-file format indicating the start of a structure.*/
	public static final String DOT_FILE_OPEN_BRACKET = "{";
	/** String for the dot-file format indicating the end of a structure.*/
	public static final String DOT_FILE_CLOSE_BRACKET = "}";
	/**	The start String for a dot-file. */
	public static final String DOT_FILE_START = DOT_FILE_DIGRAPH + " " 
	+ DOT_FILE_OPEN_BRACKET;
	/** The end String for a dot-file. */
	public static final String DOT_FILE_END = "}";	
	/** String for the dot-file format seperating numbers. */
	public static final String DOT_FILE_DASH = "-";
	/** String for the dot-file format starting a label. */
	public static final String DOT_FILE_LABEL_START = "label";
	/** String for the dot-file format ending a label. */
	public static final String DOT_FILE_LABEL_END = "\"]";
	
	//edge Strings
	
	/** String for the dot-file format indicating an edge. */
	public static final String DOT_FILE_EDGE = "->";
	/** String for the dot-file format indicating a bold style. */
	public static final String DOT_FILE_MARKUP = "[style=bold]";
	/** String for the dot-file format indicating a source. */
	public static final String DOT_FILE_SOURCE = "[shape=record]";
	/** String for the dot-file format indicating sink. */
	public static final String DOT_FILE_DESTINATION = "[shape=circle]";
	/** String for the dot-file format ending a line. */
	public static final String DOT_FILE_LINE_END = ";";
	
	//output file Strings
	
	/** A String for the output file name. */
	public static final String DOT_FILE_FROM = "from";
	/** A String for the output file name. */
	public static final String DOT_FILE_TO = "to";
	/** A String for the output file name. */
	public static final String DOT_FILE_DISTANCE = "distance";
	/** A String for the output file name. */
	public static final String DOT_FILE_TIME = "time";	
}
