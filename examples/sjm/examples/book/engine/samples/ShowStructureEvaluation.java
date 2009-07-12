package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.Atom;

/**
 * Show the evaluation of a structure.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowStructureEvaluation {
	/**
	 * Show the evaluation of a structure.
	 */
	public static void main(String args[]) {
		Atom a = new Atom("maine");
		Object o = a.eval();
		System.out.println("" + a + o);
	}
}
