package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.*;

/**
 * Show a couple of comparisons.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowComparison {
	/**
	 * Show a couple of comparisons.
	 */
	public static void main(String[] args) {
		Atom alt1 = new Atom(new Integer(5280));
		Atom alt2 = new Atom(new Integer(19));

		Query q1 = new Query(null, // no axiom source
				new Comparison(">", alt1, alt2));

		System.out.println(q1 + " : " + q1.canFindNextProof());

		Query q2 = new Query(null, new Comparison(">", new Atom("denver"), new Atom("richmond")));

		System.out.println(q2 + " : " + q2.canFindNextProof());
	}
}
