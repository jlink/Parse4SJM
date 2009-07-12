package sjm.examples.book.query;

import sjm.examples.book.engine.Term;
import sjm.parse.*;

/**
 * This assembler pops a term and passes it to a query
 * builder.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SelectTermAssembler implements IAssembler {
	/**
	 * Pop a term and pass it to a query builder.
	 */
	public void workOn(Assembly a) {
		QueryBuilder b = (QueryBuilder) a.getTarget();
		Term t = (Term) a.pop();
		b.addTerm(t);
	}
}
