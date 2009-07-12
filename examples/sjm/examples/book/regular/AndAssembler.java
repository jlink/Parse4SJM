package sjm.examples.book.regular;

import sjm.parse.*;

/**
 * Pop two Parsers from the stack and push a new <code>
 * Sequence</code> of them.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AndAssembler implements IAssembler {
	/**
	 * Pop two parsers from the stack and push a new 
	 * <code>Sequence</code> of them.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		Object top = a.pop();
		Sequence s = new Sequence();
		s.add((Parser) a.pop());
		s.add((Parser) top);
		a.push(s);
	}
}
