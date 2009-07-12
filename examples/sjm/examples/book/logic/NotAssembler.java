package sjm.examples.book.logic;

import sjm.examples.book.engine.*;
import sjm.parse.*;

/**
 * Pops a structure from the top of the stack and pushes a Not
 * version of it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NotAssembler implements IAssembler {
	/**
	 * Pops a structure from the top of the stack and pushes a Not
	 * version of it.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		Structure s = (Structure) a.pop();
		a.push(new Not(s));
	}
}
