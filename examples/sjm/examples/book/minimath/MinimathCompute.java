package sjm.examples.book.minimath;

import sjm.examples.book.arithmetic.*;
import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class provides a parser that recognizes minimal 
 * arithmetic expressions, specifically allowing only the
 * '-' operator. The rules of the Minimath language are:
 * 
 * <blockquote><pre>	
 *     e = Num m*;
 *     m = '-' Num;
 * </pre></blockquote>
 *
 * This class shows, in a minimal example, where assemblers 
 * plug into a parser composite.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MinimathCompute {
	/**
	 * Just a little demo.
	 */
	public static void main(String args[]) {
		Sequence e = new Sequence();

		Num n = new Num();
		n.setAssembler(new NumAssembler());

		e.add(n);

		Sequence m = new Sequence();
		m.add(new Symbol('-').discard());
		m.add(n);
		m.setAssembler(new MinusAssembler());

		e.add(new Repetition(m));

		TokenAssembly t = new TokenAssembly("25 - 16 - 9");
		Assembly out = e.completeMatch(t);
		System.out.println(out.pop());
	}
}
