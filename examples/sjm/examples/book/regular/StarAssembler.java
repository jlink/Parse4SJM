package sjm.examples.book.regular;

import sjm.parse.*;

/**
 * Pop a parser from the stack and push a new <code>
 * Repetition</code> of it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */

public class StarAssembler implements IAssembler {

	/**
	 * Pop a parser from the stack and push a new <code>
	 * Repetition</code> of it.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		a.push(new Repetition((Parser) a.pop()));
	}
}
