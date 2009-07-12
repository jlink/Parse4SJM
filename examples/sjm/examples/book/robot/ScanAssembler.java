package sjm.examples.book.robot;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Sets an assembly's target to be a ScanCommand and note its
 * location.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ScanAssembler implements IAssembler {
	/**
	 * Sets an assembly's target to be a 
	 * <code>ScanCommand</code> object and note its location.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		ScanCommand sc = new ScanCommand();
		Token t = (Token) a.pop();
		sc.setLocation(t.sval());
		a.setTarget(sc);
	}
}
