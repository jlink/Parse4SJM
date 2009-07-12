package sjm.examples.book.logic;

import sjm.examples.book.engine.*;
import sjm.parse.*;

/**
 * Pops two terms, constructs an Evaluation from these terms,
 * and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class EvaluationAssembler implements IAssembler {
	/**
	 * Pops two terms, constructs an Evaluation from these terms,
	 * and pushes it.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		Term second = (Term) a.pop();
		Term first = (Term) a.pop();
		a.push(new Evaluation(first, second));
	}
}
