package sjm.examples.book.mechanics;

import java.util.Set;

import sjm.parse.Parser;
import sjm.parse.tokens.*;

/**
 * This class shows the how to introduce a new type of 
 * terminal, specifically for recognizing lowercase words.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class LowercaseWord extends Word {

	/**
	 * Returns true if an assembly's next element is a 
	 * lower case word.
	 *
	 * @param   object   an element from a assembly
	 *
	 * @return   true, if an assembly's next element is a 
	 *           lowercase word
	 */
	protected boolean qualifies(Object o) {
		Token t = (Token) o;
		if (!t.isWord()) {
			return false;
		}
		String word = t.sval();
		return word.length() > 0 && Character.isLowerCase(word.charAt(0));
	}

	@Override
	protected char workOnCharForRandomExpansion(char c, int pos) {
		if (pos == 0)
			return Character.toLowerCase(c);
		return c;
	}

	/**
	 * Returns a textual description of this production.
	 *
	 * @param   vector   a list of productions already printed in this
	 *                   description
	 * 
	 * @return   string   a textual description of this production
	 *
	 * @see ProductionRule#toString()
	 */
	public String unvisitedString(Set<Parser> visited) {
		return "word";
	}
}
