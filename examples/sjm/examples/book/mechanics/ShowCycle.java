package sjm.examples.book.mechanics;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show that a parser that contains a cycle prints 
 * itself without looping.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCycle {
	/**
	 * Show that a parser that contains a cycle prints 
	 * itself without looping.
	 */
	public static void main(String args[]) {

		// ticks = "tick" | "tick" ticks;

		Alternation ticks = new Alternation();
		Literal tick = new Literal("tick");

		ticks.add(tick).add(new Sequence().add(tick).add(ticks));

		System.out.println(ticks.bestMatch(new TokenAssembly("tick tick tick tick")));

		System.out.println(ticks);

	}
}
