package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.*;

/**
 * Show how a query can screen for certain facts.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowScreening {
	/**
	 * Show the construction and use of a simple program.
	 */
	public static void main(String[] args) {
		Program p = ShowProgram.altitudes();

		Variable name = new Variable("Name");
		Variable height = new Variable("Height");

		Query q1 = new Query(p, new Structure("city", new Term[] { new Fact("denver"), height }));

		if (q1.canFindNextProof()) {
			System.out.println("Denver is " + height + " feet high.");
		}

		Query q2 = new Query(p, new Structure("city", new Term[] { name, new Fact(new Integer(10200)) }));

		if (q2.canFindNextProof()) {
			System.out.println(name + " is 10200 feet high.");
		}

	}
}
