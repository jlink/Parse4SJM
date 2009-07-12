package sjm.examples.book.engine.samples;

import sjm.examples.book.engine.*;

/**
 * Show how to perform arithmetic within the engine.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowArithmetic {
	/**
	 * Show how to perform arithmetic within the engine.
	 */
	public static void main(String[] args) {
		NumberFact a, b;
		a = new NumberFact(1000);
		b = new NumberFact(999);

		ArithmeticOperator x, y;
		x = new ArithmeticOperator('*', a, b);
		y = new ArithmeticOperator('+', x, b);

		System.out.println(y);
		System.out.println(y.eval());
	}
}
