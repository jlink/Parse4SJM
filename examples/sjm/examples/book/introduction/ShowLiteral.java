package sjm.examples.book.introduction;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show a parser that recognizes an int declaration.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowLiteral {
	/**
	 * Just a little demo.
	 */
	public static void main(String args[]) {
		Sequence s = new Sequence();
		s.add(new Literal("int"));
		s.add(new Word());
		s.add(new Symbol('='));
		s.add(new Num());
		s.add(new Symbol(';'));

		Assembly a = s.completeMatch(new TokenAssembly("int i = 3;"));

		System.out.println(a);
	}
}
