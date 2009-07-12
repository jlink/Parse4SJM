package sjm.examples.book.tokens;

import java.io.IOException;

import sjm.parse.tokens.*;

/**
 * This class demonstrates how <code>QuoteState</code> 
 * works.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowQuoteState {
	/**
	 * Demonstrate the operation of the quote state.
	 */
	public static void main(String args[]) throws IOException {
		Tokenizer t = new Tokenizer("Hamlet says #Alas, poor Yorick!# and " + "#To be, or not...");

		t.setCharacterState('#', '#', t.quoteState());

		while (true) {
			Token tok = t.nextToken();
			if (tok.equals(Token.EOF)) {
				break;
			}
			System.out.println(tok);
		}
	}
}
