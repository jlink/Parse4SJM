package sjm.examples.book.pretty;

import sjm.parse.*;

/**
 * Push a <code>TerminalNode</code> that contains the word
 * "empty" on the assembly's stack.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyEmptyAssembler implements IAssembler {
	/**
	 * Push a <code>TerminalNode</code> that contains the word
	 * "empty" on the assembly's stack.
	 *
	 * @param   Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		a.push(new TerminalNode("empty"));
	}
}
