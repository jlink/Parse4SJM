package sjm.examples.book.logic;

import sjm.examples.book.engine.*;
import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Pops two comparison terms and an operator, builds 
 * the comparison, and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ComparisonAssembler implements IAssembler {
	/**
	 * Pops two comparison terms and an operator, builds 
	 * the comparison, and pushes it.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		ComparisonTerm second = (ComparisonTerm) a.pop();
		ComparisonTerm first = (ComparisonTerm) a.pop();
		Token t = (Token) a.pop();
		a.push(new Comparison(t.sval(), first, second));
	}
}
