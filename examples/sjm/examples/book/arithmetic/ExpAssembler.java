package sjm.examples.book.arithmetic;

import sjm.parse.*;

/**
 * Pop two numbers from the stack and push the result of
 * exponentiating the lower number to the upper one.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ExpAssembler implements IAssembler {
	/**
	 * Pop two numbers from the stack and push the result of
	 * exponentiation the lower number to the upper one.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		Double d1 = (Double) a.pop();
		Double d2 = (Double) a.pop();
		Double d3 = new Double(Math.pow(d2.doubleValue(), d1.doubleValue()));
		a.push(d3);
	}
}
