package sjm.examples.book.logic;

import sjm.examples.book.engine.Anonymous;
import sjm.parse.*;

/**
 * Pushes an anonymous variable onto an assembly's stack.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AnonymousAssembler implements IAssembler {
	/**
	 * Pushes an anonymous variable onto an assembly's stack.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		a.push(new Anonymous());
	}
}
