package sjm.examples.book.sling;

import sjm.parse.*;

/** 
 * Pushes the function (t, pi).
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class PiAssembler implements IAssembler {
	/**
	 * Push the function (t, pi).
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		a.push(new Cartesian(new T(), new Point(0, Math.PI)));
	}
}
