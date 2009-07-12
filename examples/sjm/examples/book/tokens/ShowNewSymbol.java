package sjm.examples.book.tokens;

import java.io.IOException;

import sjm.parse.tokens.*;

/**
 * This class shows how to add a new multi-character symbol.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowNewSymbol {
	/**
	 * Demonstrate how to add a multi-character symbol.
	 */
	public static void main(String args[]) throws IOException {
		Tokenizer t = new Tokenizer("42.001 =~= 42");

		t.symbolState().add("=~=");

		while (true) {
			Token tok = t.nextToken();
			if (tok.equals(Token.EOF)) {
				break;
			}
			System.out.println(tok);
		}
	}
}
