package sjm.examples.book.coffee;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * This assembler pops a coffee's name from an assembly's
 * stack, and sets the assembly's target to be a new Coffee 
 * object with this name.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NameAssembler implements IAssembler {
	/**
	 * Pop a coffee's name from an assembly's stack, and set the
	 * assembly's target to be a new Coffee object with this name.
	 *
	 * @param   Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		Coffee c = new Coffee();
		Token t = (Token) a.pop();
		c.setName(t.sval().trim());
		a.setTarget(c);
	}
}
