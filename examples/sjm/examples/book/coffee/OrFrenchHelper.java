package sjm.examples.book.coffee;

import java.util.Vector;

/**
 * This helper sets a target coffee object's <code>
 * alsoOfferFrench</code> attribute to true.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class OrFrenchHelper extends Helper {
	/**
	 * Sets a target coffee object's <code>alsoOfferFrench
	 * </code> attribute to true. The target coffee is the last
	 * coffee in a Vector of coffees.
	 */
	public void startElement(Object target) {
		Coffee c = ((Vector<Coffee>) target).lastElement();
		c.setAlsoOfferFrench(true);
	}
}
