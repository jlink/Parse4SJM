package sjm.parse.tokens;

import java.util.*;

import sjm.parse.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A Word matches a word from a token assembly.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class Word extends Terminal {

	/**
	 * Returns true if an assembly's next element is a word.
	 * 
	 * @param object
	 *            an element from an assembly
	 * 
	 * @return true, if an assembly's next element is a word
	 */
	@Override
	protected boolean qualifies(Object o) {
		Token t = (Token) o;
		return t.isWord();
	}

	/**
	 * Create a set with one random word (with 3 to 7 characters).
	 */
	@Override
	public List<String> randomExpansion(int maxDepth, int depth) {
		int n = (int) (5.0 * Math.random()) + 3;

		char[] letters = new char[n];
		for (int i = 0; i < n; i++) {
			int c = (int) (26.0 * Math.random()) + 'a';
			letters[i] = workOnCharForRandomExpansion((char) c, i);
		}

		List<String> v = new ArrayList<String>();
		v.add(new String(letters));
		return v;
	}

	protected char workOnCharForRandomExpansion(char c, int pos) {
		return c;
	}

	/**
	 * Returns a textual description of this parser.
	 * 
	 * @param vector
	 *            a list of parsers already printed in this description
	 * 
	 * @return string a textual description of this parser
	 * 
	 * @see Parser#toString()
	 */
	@Override
	public String unvisitedString(Set<Parser> visited) {
		return "Word";
	}
}
