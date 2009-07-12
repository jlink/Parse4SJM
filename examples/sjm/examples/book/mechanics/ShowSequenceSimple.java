package sjm.examples.book.mechanics;

import java.util.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class uses a <code>VerboseSequence</code> to show the progress a
 * sequence makes during matching.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowSequenceSimple {
	/**
	 * Using a <code>VerboseSequence</code>, show the progress a sequence makes
	 * during matching.
	 */
	public static void main(String[] args) {

		Parser hello = new Literal("Hello");
		Parser world = new Literal("world");
		Parser bang = new Symbol('!');

		Parser s = new VerboseSequence().add(hello).add(world).add(bang);

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly("Hello world!"));
		s.match(v);
	}
}
