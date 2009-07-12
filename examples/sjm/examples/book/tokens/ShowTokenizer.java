package sjm.examples.book.tokens;

import java.io.IOException;

import sjm.parse.tokens.*;

/**
 * Show a default <code>Tokenizer</code> object at work.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowTokenizer {
	/**
	 * Show a default Tokenizer at work.
	 */
	public static void main(String args[]) throws IOException {

		String s =

		"\"It's 123 blast-off!\", she said, // watch out!\n" + "and <= 3 'ticks' later /* wince */ , it's blast-off!";

		System.out.println(s);
		System.out.println();

		ITokenizer t = new Tokenizer(s);

		while (true) {
			Token tok = t.nextToken();
			if (tok.equals(Token.EOF)) {
				break;
			}
			System.out.println("(" + tok + ")");
		}
	}
}
