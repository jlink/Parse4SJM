package sjm.examples.book.minimath;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show how to build a parser to recognize elements
 * of the language "Minimath", whose rules are:
 * 
 * <blockquote><pre>	
 *     e = Num m*;
 *     m = '-' Num;
 * </pre></blockquote>
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MinimathRecognize {
	/**
	 * Just a little demo.
	 */
	public static void main(String args[]) {
		Sequence e = new Sequence();

		e.add(new Num());

		Sequence m = new Sequence();
		m.add(new Symbol('-'));
		m.add(new Num());

		e.add(new Repetition(m));

		System.out.println(e.completeMatch(new TokenAssembly("25 - 16 - 9")));
	}
}
