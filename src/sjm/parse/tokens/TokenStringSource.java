package sjm.parse.tokens;

import java.io.IOException;
import java.util.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A TokenStringSource enumerates over a specified reader, returning
 * TokenStrings delimited by a specified delimiter.
 * <p>
 * For example, <blockquote>
 * 
 * <pre>
 * 
 * String s = &quot;I came; I saw; I left in peace;&quot;;
 * 
 * TokenStringSource tss = new TokenStringSource(new Tokenizer(s), &quot;;&quot;);
 * 
 * while (tss.hasMoreTokenStrings()) {
 * 	System.out.println(tss.nextTokenString());
 * }
 * 
 * </pre>
 * 
 * </blockquote>
 * 
 * prints out:
 * 
 * <blockquote>
 * 
 * <pre>
 *     I came
 *     I saw
 *     I left in peace
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */

public class TokenStringSource {
	protected ITokenizer tokenizer;
	protected String delimiter;
	protected TokenString cachedTokenString = null;

	/**
	 * Constructs a TokenStringSource that will read TokenStrings using the
	 * specified tokenizer, delimited by the specified delimiter.
	 * 
	 * @param tokenizer
	 *            a tokenizer to read tokens from
	 * 
	 * @param delimiter
	 *            the character that fences off where one TokenString ends and
	 *            the next begins
	 * 
	 * @returns a TokenStringSource that will read TokenStrings from the
	 *          specified tokenizer, delimited by the specified delimiter
	 */
	public TokenStringSource(ITokenizer tokenizer, String delimiter) {

		this.tokenizer = tokenizer;
		this.delimiter = delimiter;
	}

	/**
	 * The design of <code>nextTokenString</code> is that is always returns a
	 * cached value. This method will (at least attempt to) load the cache if
	 * the cache is empty.
	 */
	protected void ensureCacheIsLoaded() {
		if (cachedTokenString == null) {
			loadCache();
		}
	}

	/**
	 * Returns true if the source has more TokenStrings.
	 * 
	 * @return true, if the source has more TokenStrings that have not yet been
	 *         popped with <code>
	*           nextTokenString</code>.
	 */
	public boolean hasMoreTokenStrings() {
		ensureCacheIsLoaded();
		return cachedTokenString != null;
	}

	/**
	 * Loads the next TokenString into the cache, or sets the cache to null if
	 * the source is out of tokens.
	 */
	protected void loadCache() {
		List<Token> tokenList = nextVector();
		if (tokenList.isEmpty()) {
			cachedTokenString = null;
		} else {
			Token tokens[] = new Token[tokenList.size()];
			tokenList.toArray(tokens);
			cachedTokenString = new TokenString(tokens);
		}
	}

	/**
	 * Shows the example in the class comment.
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String args[]) {

		String s = "I came; I saw; I left in peace;";

		TokenStringSource tss = new TokenStringSource(new Tokenizer(s), ";");

		while (tss.hasMoreTokenStrings()) {
			System.out.println(tss.nextTokenString());
		}
	}

	/**
	 * Returns the next TokenString from the source.
	 * 
	 * @return the next TokenString from the source
	 */
	public TokenString nextTokenString() {
		ensureCacheIsLoaded();
		TokenString returnTokenString = cachedTokenString;
		cachedTokenString = null;
		return returnTokenString;
	}

	/**
	 * Returns a Vector of the tokens in the source up to either the delimiter
	 * or the end of the source.
	 * 
	 * @return a Vector of the tokens in the source up to either the delimiter
	 *         or the end of the source.
	 */
	protected List<Token> nextVector() {
		List<Token> v = new ArrayList<Token>();
		try {
			while (true) {
				Token tok = tokenizer.nextToken();
				if (tok.ttype() == Token.TT_EOF || tok.sval().equals(delimiter)) {
					break;
				}
				v.add(tok);
			}
		} catch (IOException e) {
			throw new InternalError("Problem tokenizing string: " + e);
		}
		return v;
	}
}
