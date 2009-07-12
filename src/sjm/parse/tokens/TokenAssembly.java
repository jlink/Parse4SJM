package sjm.parse.tokens;

import sjm.parse.Assembly;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A TokenAssembly is an Assembly whose elements are Tokens. Tokens are,
 * roughly, the chunks of text that a <code>
 * Tokenizer</code> returns.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */

public class TokenAssembly extends Assembly {
	/**
	 * the "string" of tokens this assembly will consume
	 */
	protected TokenString tokenString;

	/**
	 * Constructs a TokenAssembly on a TokenString constructed from the given
	 * String.
	 * 
	 * @param string
	 *            the string to consume
	 * 
	 * @return a TokenAssembly that will consume a tokenized version of the
	 *         supplied String
	 */
	public TokenAssembly(String s) {
		this(new TokenString(s));
	}

	/**
	 * Constructs a TokenAssembly on a TokenString constructed from the given
	 * Tokenizer.
	 * 
	 * @param Tokenizer
	 *            the tokenizer to consume tokens from
	 * 
	 * @return a TokenAssembly that will consume a tokenized version of the
	 *         supplied Tokenizer
	 */
	public TokenAssembly(ITokenizer t) {
		this(new TokenString(t));
	}

	/**
	 * Constructs a TokenAssembly from the given TokenString.
	 * 
	 * @param tokenString
	 *            the tokenString to consume
	 * 
	 * @return a TokenAssembly that will consume the supplied TokenString
	 */
	public TokenAssembly(TokenString tokenString) {
		this.tokenString = tokenString;
	}

	/**
	 * Returns a textual representation of the amount of this tokenAssembly that
	 * has been consumed.
	 * 
	 * @param delimiter
	 *            the mark to show between consumed elements
	 * 
	 * @return a textual description of the amount of this assembly that has
	 *         been consumed
	 */
	@Override
	public String consumed(String delimiter) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < elementsConsumed(); i++) {
			if (i > 0) {
				buf.append(delimiter);
			}
			buf.append(tokenString.tokenAt(i));
		}
		return buf.toString();
	}

	/**
	 * Returns the default string to show between elements consumed or
	 * remaining.
	 * 
	 * @return the default string to show between elements consumed or remaining
	 */
	@Override
	public String defaultDelimiter() {
		return "/";
	}

	/**
	 * Returns the number of elements in this assembly.
	 * 
	 * @return the number of elements in this assembly
	 */
	@Override
	public int length() {
		return tokenString.length();
	}

	/**
	 * Returns the next token.
	 * 
	 * @return the next token from the associated token string.
	 * 
	 * @exception ArrayIndexOutOfBoundsException
	 *                if there are no more tokens in this tokenizer's string.
	 */
	public Object next() {
		return tokenString.tokenAt(index++);
	}

	/**
	 * Shows the next object in the assembly, without removing it
	 * 
	 * @return the next object
	 * 
	 */
	@Override
	public Object peek() {
		if (index < length()) {
			return tokenString.tokenAt(index);
		} else {
			return null;
		}
	}

	/**
	 * Returns a textual representation of the amount of this tokenAssembly that
	 * remains to be consumed.
	 * 
	 * @param delimiter
	 *            the mark to show between consumed elements
	 * 
	 * @return a textual description of the amount of this assembly that remains
	 *         to be consumed
	 */
	@Override
	public String remainder(String delimiter) {
		StringBuffer buf = new StringBuffer();
		for (int i = elementsConsumed(); i < tokenString.length(); i++) {

			if (i > elementsConsumed()) {
				buf.append(delimiter);
			}
			buf.append(tokenString.tokenAt(i));
		}
		return buf.toString();
	}
}
