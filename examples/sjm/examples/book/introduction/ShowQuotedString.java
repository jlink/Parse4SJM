package sjm.examples.book.introduction;

import sjm.parse.IParser;
import sjm.parse.tokens.*;

/**
 * Show how to recognize a quoted string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowQuotedString {
	/**
	 *  Show how to recognize a quoted string.
	 */
	public static void main(String[] args) {
		IParser p = new QuotedString();
		String id = "\"Clark Kent\"";
		System.out.println(p.bestMatch(new TokenAssembly(id)));
	}
}
