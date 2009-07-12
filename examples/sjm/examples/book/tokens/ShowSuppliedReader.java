package sjm.examples.book.tokens;

import java.io.*;

import sjm.parse.tokens.*;

/**
 * This class shows that you can supply your own reader to
 * a tokenizer.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowSuppliedReader {
	/**
	 * Just an example.
	 */
	public static void main(String[] args) throws IOException {
		String s = "Let's file this away.";
		FileWriter fw = new FileWriter("temp.txt");
		fw.write(s);
		fw.close();

		FileReader fr = new FileReader("temp.txt");
		PushbackReader pr = new PushbackReader(fr, 4);

		Tokenizer t = new Tokenizer();
		t.setReader(pr);

		while (true) {
			Token tok = t.nextToken();
			if (tok.equals(Token.EOF)) {
				break;
			}
			System.out.println(tok);
		}
	}
}
