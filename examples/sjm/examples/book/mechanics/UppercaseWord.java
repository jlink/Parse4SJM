package sjm.examples.book.mechanics;

import java.util.*;

import sjm.parse.Parser;
import sjm.parse.tokens.*;

/**
 * This class shows the how to introduce a new type of 
 * terminal, specifically for recognizing uppercase words.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class UppercaseWord extends Word {

	/**
	 * Returns true if an assembly's next element is an upper 
	 * case word.
	 *
	 * @param   object   an element from a assembly
	 *
	 * @return   true, if a assembly's next element is an upper 
	 *           case word
	 */
	protected boolean qualifies(Object o) {
		Token t = (Token) o;
		if (!t.isWord()) {
			return false;
		}
		String word = t.sval();
		return word.length() > 0 && Character.isUpperCase(word.charAt(0));
	}

	/**
	 * Create a set with one random uppercase word (with 3
	 * to 7 characters).
	 */
	public List<String> randomExpansion(int maxDepth, int depth) {
		int n = (int) (5.0 * Math.random()) + 3;

		char[] letters = new char[n];
		for (int i = 0; i < n; i++) {
			int c = (int) (26.0 * Math.random()) + 'A';
			letters[i] = (char) c;
		}

		List<String> v = new ArrayList<String>();
		v.add(new String(letters));
		return v;
	}

	/**
	 * Returns a textual description of this production.
	 *
	 * @param   vector   a list of productions already printed 
	 *                   in this description
	 * 
	 * @return   string   a textual description of this production
	 *
	 * @see ProductionRule#toString()
	 */
	public String unvisitedString(Set<Parser> visited) {
		return "Word";
	}
}
