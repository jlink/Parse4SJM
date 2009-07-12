package sjm.examples.book.coffee;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * This assembler pops a string, and sets the target coffee's
 * former name to this string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class FormerNameAssembler implements IAssembler {
	/**
	 * Pop a string, and set the target coffee's former name to
	 * this string.
	 *
	 * @param   Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		Token t = (Token) a.pop();
		Coffee c = (Coffee) a.getTarget();
		c.setFormerName(t.sval().trim());
	}
}
