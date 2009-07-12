package sjm.examples.book.mechanics;

import java.util.*;

import sjm.parse.*;
import sjm.parse.chars.*;

/**
 * This class shows that the "right" answer for a repetition 
 * object is not always to match all that it can.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAstarAB {
	/** 
	 * This class shows that the "right" answer for a repetition 
	 * object is not always to match all that it can.
	 */
	public static void main(String[] args) {

		Parser aStar = new Repetition(new SpecificChar('a'));

		Parser ab = new Sequence().add(new SpecificChar('a')).add(new SpecificChar('b')); // ab

		Parser aStarAB = new Sequence().add(aStar).add(ab); // a*ab

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new CharacterAssembly("aaaab"));

		System.out.println(aStar.match(v));
		System.out.println(ab.match(aStar.match(v)));
		System.out.println(aStarAB.match(v));
	}
}
