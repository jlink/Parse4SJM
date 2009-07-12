package sjm.examples.book.query;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Pops a class name, and informs a QueryBuilder that this
 * is a class to select from.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ClassNameAssembler implements IAssembler {

	/**
	 * Pop a class name, and inform a QueryBuilder that this
	 * is a class to select from.
	 */
	public void workOn(Assembly a) {
		QueryBuilder b = (QueryBuilder) a.getTarget();
		Token t = (Token) a.pop();
		b.addClassName(t.sval());
	}
}
