package sjm.examples.book.regular;

import sjm.parse.*;

/**
 * Pop two parsers from the stack and push a new <code>
 * Alternation</code> of them.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */

public class OrAssembler implements IAssembler {

	/**
	 * Pop two parsers from the stack and push a new
	 * <code>Alternation</code> of them.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		Object top = a.pop();
		Alternation alt = new Alternation();
		alt.add((Parser) a.pop());
		alt.add((Parser) top);
		a.push(alt);
	}
}
