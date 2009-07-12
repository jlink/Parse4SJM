package sjm.parse.tokens;

import java.io.*;
import java.math.BigDecimal;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A whitespace state ignores whitespace (such as blanks and tabs), and returns
 * the tokenizer's next token. By default, all characters from 0 to 32 are
 * whitespace.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class WhitespaceState extends TokenizerState {
	protected boolean whitespaceChar[] = new boolean[256];
	protected boolean whitespaceTokensEnabled = false;

	/**
	 * Constructs a whitespace state with a default idea of what characters are,
	 * in fact, whitespace.
	 * 
	 * @return a state for ignoring whitespace
	 */
	public WhitespaceState() {
		setWhitespaceChars(0, ' ', true);
	}

	public void enableWhitespaceTokens() {
		whitespaceTokensEnabled = true;
	}

	/**
	 * Ignore whitespace (such as blanks and tabs), and return the tokenizer's
	 * next token.
	 * 
	 * @return the tokenizer's next token
	 */
	@Override
	public Token nextToken(PushbackReader r, int aWhitespaceChar, Tokenizer t) throws IOException {

		if (whitespaceTokensEnabled)
			return nextTokenWithWhitespaceTokenizing(r, aWhitespaceChar, t);
		return nextTokenWithoutWhitespaceTokenizing(r, t);
	}

	private Token nextTokenWithWhitespaceTokenizing(PushbackReader r, int unicode, Tokenizer t) throws IOException {
		StringBuilder whitespace = new StringBuilder();
		do {
			whitespace.append((char) unicode);
			unicode = r.read();
		} while (isWhitespaceChar(unicode));

		if (unicode >= 0) {
			r.unread(unicode);
		}
		return new Token(Token.TT_WHITESPACE, whitespace.toString(), BigDecimal.ZERO);
	}

	private Token nextTokenWithoutWhitespaceTokenizing(PushbackReader r, Tokenizer t) throws IOException {
		int c;
		do {
			c = r.read();
		} while (isWhitespaceChar(c));

		if (c >= 0) {
			r.unread(c);
		}
		return t.nextToken();
	}

	private boolean isWhitespaceChar(int c) {
		return c >= 0 && c < whitespaceChar.length && whitespaceChar[c];
	}

	/**
	 * Establish the given characters as whitespace to ignore.
	 * 
	 * @param first
	 *            char
	 * 
	 * @param second
	 *            char
	 * 
	 * @param boolean true, if this state should ignore characters in the given
	 *        range
	 */
	public void setWhitespaceChars(int from, int to, boolean b) {
		for (int i = from; i <= to; i++) {
			if (i >= 0 && i < whitespaceChar.length) {
				whitespaceChar[i] = b;
			}
		}
	}
}
