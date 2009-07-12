package sjm.examples.book.coffee;

import java.util.Vector;

/**
 * This helper sets a target coffee object's <code>price
 * </code> attribute.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PriceHelper extends Helper {
	/**
	 * Sets a target coffee object's <code>price</code> 
	 * attribute to the double value of the given string.
	 * The target coffee is the last coffee in a Vector of 
	 * coffees.
	 */
	public void characters(String s, Object target) {
		Coffee c = ((Vector<Coffee>) target).lastElement();
		c.setPrice(Double.valueOf(s).doubleValue());
	}
}
