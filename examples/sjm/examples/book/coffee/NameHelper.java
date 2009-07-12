package sjm.examples.book.coffee;

import java.util.Vector;

/**
 * This helper sets a target coffee object's <code>name
 * </code> attribute.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class NameHelper extends Helper {
	/**
	 * Sets a target coffee object's <code>name</code> 
	 * attribute to the given string. The target coffee is
	 * the last coffee in a Vector of coffees.
	 */
	public void characters(String s, Object target) {
		Coffee c = ((Vector<Coffee>) target).lastElement();
		c.setName(s);
	}
}
