package sjm.examples.book.introduction;

import sjm.parse.tokens.TokenAssembly;

/**
 * Show that apostrophes can be parts of words and can contain quoted strings.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowApostrophe {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {
		String s = "Let's 'rock and roll'!";
		TokenAssembly a = new TokenAssembly(s);
		while (a.hasNext()) {
			System.out.println(a.next());
		}
	}
}
