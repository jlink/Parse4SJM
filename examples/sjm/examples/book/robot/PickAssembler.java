package sjm.examples.book.robot;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Sets an assembly's target to be a <code>PickCommand
 * </code> and note its location.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class PickAssembler implements IAssembler {
	/**
	 * Sets an assembly's target to be a 
	 * <code>PickCommand</code> object and note its location.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		PickCommand pc = new PickCommand();
		Token t = (Token) a.pop();
		pc.setLocation(t.sval());
		a.setTarget(pc);
	}
}
