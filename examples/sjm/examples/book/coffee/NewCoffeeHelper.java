package sjm.examples.book.coffee;

import java.util.Vector;

/**
 * This helper adds a new coffee object to the end of
 * a vector of coffees.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class NewCoffeeHelper extends Helper {
	/**
	 * Add a new coffee object to the end of a vector of coffees.
	 */
	public void startElement(Object target) {
		Vector<Coffee> v = (Vector<Coffee>) target;
		v.add(new Coffee());
	}
}
