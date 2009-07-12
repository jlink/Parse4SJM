package sjm.examples.book.coffee;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Pops a number and sets the target coffee's price to this
 * number.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class PriceAssembler implements IAssembler {
	/**
	 * Pop a number, and set the target coffee's price to this
	 * string.
	 *
	 * @param   Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		Token t = (Token) a.pop();
		Coffee c = (Coffee) a.getTarget();
		c.setPrice(t.nval());
	}
}
