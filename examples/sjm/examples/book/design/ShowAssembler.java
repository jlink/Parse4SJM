package sjm.examples.book.design;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show how to use an assembler. The example shows how to
 * calculate the average length of words in a string.
 *
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAssembler {
	/**
	 * Show how to use an assembler to calculate the average 
	 * length of words in a string.
	 */
	public static void main(String args[]) {

		// As Polonius says, in "Hamlet"...
		String quote = "Brevity is the soul of wit";

		Assembly in = new TokenAssembly(quote);
		in.setTarget(new RunningAverage());

		Word w = new Word();
		w.setAssembler(new AverageAssembler());
		Parser p = new Repetition(w);

		Assembly out = p.completeMatch(in);

		RunningAverage avg = (RunningAverage) out.getTarget();
		System.out.println("Average word length: " + avg.average());
	}
}
