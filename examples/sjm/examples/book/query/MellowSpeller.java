package sjm.examples.book.query;

/**
 * This class provides a speller that allows any spelling,
 * which facilitates random testing of Jaql language
 * elements.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 *
 * @see JaqlUe
 */
public class MellowSpeller implements Speller {
	/**
	 * Allow any spelling of any class name.
	 */
	public String getClassName(String s) {
		return s;
	}

	/**
	 * Allow any spelling of any variable name.
	 */
	public String getVariableName(String s) {
		return s;
	}
}
