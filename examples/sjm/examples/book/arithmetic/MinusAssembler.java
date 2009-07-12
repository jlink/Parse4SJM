package sjm.examples.book.arithmetic;

import sjm.parse.*;

/**
 * Pop two numbers from the stack and push the result of
 * subtracting the top number from the one below it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class MinusAssembler implements IAssembler {
	/**
	 * Pop two numbers from the stack and push the result of
	 * subtracting the top number from the one below it.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		Double d1 = (Double) a.pop();
		Double d2 = (Double) a.pop();
		Double d3 = new Double(d2.doubleValue() - d1.doubleValue());
		a.push(d3);
	}
}
