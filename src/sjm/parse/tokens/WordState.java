package sjm.parse.tokens;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * A wordState returns a word from a reader. Like other states, a tokenizer
 * transfers the job of reading to this state, depending on an initial
 * character. Thus, the tokenizer decides which characters may begin a word, and
 * this state determines which characters may appear as a second or later
 * character in a word. These are typically different sets of characters; in
 * particular, it is typical for digits to appear as parts of a word, but not as
 * the initial character of a word.
 * 
 * <p>
 * By default, the following characters may appear in a word. The method
 * <code>setWordChars()</code> allows customizing this.
 * 
 * <blockquote>
 * 
 * <pre>
 *     From    To
 *      'a', 'z'
 *      'A', 'Z'
 *      '0', '9'
 * 
 *     as well as: minus sign, underscore, and apostrophe.
 * 
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class WordState extends TokenizerState {
	protected Set<Integer> wordChars = new HashSet<Integer>();

	/**
	 * Constructs a word state with a default idea of what characters are
	 * admissible inside a word (as described in the class comment).
	 * 
	 * @return a state for recognizing a word
	 */
	public WordState() {
		setWordChars('a', 'z', true);
		setWordChars('A', 'Z', true);
		setWordChars('0', '9', true);
		setWordChars('-', '-', true);
		setWordChars('_', '_', true);
		setWordChars('\'', '\'', true);
		setWordChars(0xc0, 0xff, true);
	}

	/**
	 * Return a word token from a reader.
	 * 
	 * @return a word token from a reader
	 */
	@Override
	public Token nextToken(PushbackReader r, int unicode, Tokenizer t) throws IOException {
		StringBuilder word = new StringBuilder();
		do {
			word.append((char) unicode);
			unicode = r.read();
		} while (isWordChar(unicode));

		if (unicode >= 0) {
			r.unread(unicode);
		}
		return new Token(Token.TT_WORD, word.toString(), BigDecimal.ZERO);
	}

	/**
	 * Establish characters in the given range as valid characters for part of a
	 * word after the first character. Note that the tokenizer must determine
	 * which characters are valid as the beginning character of a word.
	 * 
	 * @param fromUnicode
	 *            char
	 * 
	 * @param toUnicode
	 *            char
	 * 
	 * @param boolean true, if this state should allow characters in the given
	 *        range as part of a word
	 */
	public void setWordChars(int fromUnicode, int toUnicode, boolean allowedInWord) {
		for (int i = fromUnicode; i <= toUnicode; i++) {
			setWordChar(i, allowedInWord);
		}
	}

	private void setWordChar(int unicode, boolean allowedInWord) {
		if (unicode < 0)
			return;
		if (allowedInWord) {
			wordChars.add(unicode);
		} else {
			wordChars.remove(unicode);
		}
	}

	/*
	 * is character a word char?
	 */
	protected boolean isWordChar(int unicode) {
		return wordChars.contains(unicode);
	}
}
