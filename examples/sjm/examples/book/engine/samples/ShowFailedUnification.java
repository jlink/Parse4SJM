package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.*;

/**
 * This class shows two structures that fail to unify.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowFailedUnification {
	/**
	 * Show two structures that fail to unify.
	 */
	public static void main(String args[]) {
		Structure city1 = new Structure("city", new Term[] { new Structure("denver"), new Structure(new Integer(5280)) });

		Structure city2 = new Structure("city", new Term[] { new Structure("jacksonville"), new Structure(new Integer(8)) });

		System.out.println(city1);
		System.out.println(city2);
		System.out.println(city1.unify(city2));

	}
}
