package sjm.examples.book.tokens;

import sjm.examples.book.arithmetic.ArithmeticParser;
import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class shows how to use a tokenizer that accepts 
 * scientific notation with an arithmetic parser. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowScientific {
	/**
	 * Show how to use a tokenizer that accepts scientific 
	 * notation with an arithmetic parser.
	 */
	public static void main(String[] args) throws Exception {

		Tokenizer t = new Tokenizer();
		ScientificNumberState sns = new ScientificNumberState();
		t.setCharacterState('0', '9', sns);
		t.setCharacterState('.', '.', sns);
		t.setCharacterState('-', '-', sns);

		t.setString("1e2 + 1e1 + 1e0 + 1e-1 + 1e-2 + 1e-3");

		IParser p = ArithmeticParser.start();
		Assembly a = p.bestMatch(new TokenAssembly(t));
		System.out.println(a.pop());
	}
}
