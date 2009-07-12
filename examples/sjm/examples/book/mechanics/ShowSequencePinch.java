package sjm.examples.book.mechanics;

import java.util.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class shows that a <code>Sequence</code> match may
 * widen and then narrow the state of a match.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowSequencePinch {
	/**
	 * Show that a <code>Sequence</code> match may widen and then
	 * narrow the state of a match.
	 */
	public static void main(String[] args) {

		Parser s = new VerboseSequence().add(new Repetition(new Word())).add(new Symbol('!'));

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly("Hello world!"));
		s.match(v);
	}
}
