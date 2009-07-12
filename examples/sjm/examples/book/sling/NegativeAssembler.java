package sjm.examples.book.sling;

import sjm.parse.*;

/**
 * Pop the assembly, and push a new function that multiplies
 * this function by -1.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NegativeAssembler implements IAssembler {
	/**
	 * Push the point (-1, -1), and ask an <code>Arithmetic</code> 
	 * "times" object to work on the assembly. The arithmetic 
	 * function will pop the point and will pop whatever function 
	 * was on top of the stack previously. The arithmetic function 
	 * will then form a multiplication function from these 
	 * elements and push this new function. 
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		// the term is already there, so push second term
		a.push(new Point(-1, -1));
		new FunctionAssembler(new Arithmetic('*')).workOn(a);
	}
}
