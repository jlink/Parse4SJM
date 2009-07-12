package sjm.examples.book.minimath;

import sjm.examples.book.arithmetic.*;
import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class uses a problematic grammar for Minimath. For
 * a better grammar, see class <code>MinimathCompute</code>. 
 * Here, the grammar is:
 * 
 * <blockquote><pre>	
 *     e = Num '-' e | Num;
 * </pre></blockquote>
 *
 * Writing a parser directly from this grammar will show
 * that the associativity is wrong. For example, this 
 * grammar will lead to a parser that calculates the value 
 * of 25 - 16 - 9 as 18.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MiniWrongAssociativity {
	/**
	 * Demonstrates incorrect associativity.
	 */
	public static void main(String args[]) {
		Alternation e = new Alternation();
		Num n = new Num();
		n.setAssembler(new NumAssembler());

		Sequence s = new Sequence();
		s.add(n);
		s.add(new Symbol('-').discard());
		s.add(e);
		s.setAssembler(new MinusAssembler());

		e.add(s);
		e.add(n);

		Assembly out = e.completeMatch(new TokenAssembly("25 - 16 - 9"));

		System.out.println(out.pop() + " // arguably wrong!");
	}
}
