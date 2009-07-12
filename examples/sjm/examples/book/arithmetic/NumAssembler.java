package sjm.examples.book.arithmetic;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Replace the top token in the stack with the token's
 * Double value.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NumAssembler implements IAssembler {
	/**
	 * Replace the top token in the stack with the token's
	 * Double value.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		Token t = (Token) a.pop();
		a.push(new Double(t.nval()));
	}
}
