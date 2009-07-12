package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.*;

/**
 * Show a rule in action.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRule {
	/**
	 * Show a rule in action.
	 */
	public static void main(String[] args) {
		Program p = ShowProgram.altitudes(); // from above

		Variable name = new Variable("Name");
		Variable alt = new Variable("Alt");
		Atom fiveThou = new Atom(new Integer(5000));

		Rule r = new Rule(new Structure[] { new Structure("highCity", new Term[] { name }), new Structure("city", new Term[] { name, alt }), new Comparison(">", alt, fiveThou) });

		System.out.println(r);

		p.addAxiom(r);

		Query q = new Query(p, new Structure("highCity", new Term[] { name }));

		while (q.canFindNextProof()) {
			System.out.println(q);
		}
	}
}
