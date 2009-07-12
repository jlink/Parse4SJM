package sjm.examples.book.tokens;

import java.io.IOException;
import java.math.BigDecimal;

import sjm.parse.tokens.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows some aspects of default tokenization.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowDefaultTokenization {
	/**
	 * Show some aspects of default tokenization.
	 */
	public static void main(String args[]) throws IOException {
		ITokenizer t = new Tokenizer(">give 2receive");

		Token manual[] = new Token[] { new Token(Token.TT_SYMBOL, ">", BigDecimal.ZERO), new Token(Token.TT_WORD, "give", BigDecimal.ZERO), new Token(Token.TT_NUMBER, "", new BigDecimal("2.0")), new Token(Token.TT_WORD, "receive", BigDecimal.ZERO) };

		for (int i = 0; i < 4; i++) {
			Token tok = t.nextToken();
			if (tok.equals(manual[i])) {
				System.out.print("ok! ");
			}
		}
	}
}
