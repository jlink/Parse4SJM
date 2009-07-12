package sjm.examples.book.design;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * This assembler updates a running average.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class AverageAssembler implements IAssembler {
	/**
	 * Increases a running average, by the length of the string
	 * on the stack.
	 */
	public void workOn(Assembly a) {
		Token t = (Token) a.pop();
		String s = t.sval();
		RunningAverage avg = (RunningAverage) a.getTarget();
		avg.add(s.length());
	}
}
