package sjm.examples.book.mechanics;

import sjm.parse.*;
import sjm.parse.tokens.TokenAssembly;

/**
 * Show the use of new subclasses of <code>Terminal</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowNewTerminals {
	/**
	 * Show the use of new subclasses of <code>Terminal</code>.
	 */
	public static void main(String[] args) {

		/*  term     = variable | known;
		 *  variable = UppercaseWord;
		 *  known    = LowercaseWord;
		 */

		Parser variable = new UppercaseWord();
		Parser known = new LowercaseWord();

		Parser term = new Alternation().add(variable).add(known);

		// anonymous Assembler subclasses note element type

		variable.setAssembler(new IAssembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("VAR(" + o + ")");
			}
		});

		known.setAssembler(new IAssembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("KNOWN(" + o + ")");
			}
		});

		// term* matching against knowns and variables:

		System.out.println(new Repetition(term).bestMatch(new TokenAssembly("member X republican democrat")));
	}
}
