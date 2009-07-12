package sjm.examples.book.introduction;

import sjm.parse.tokens.TokenAssembly;

/**
 * Show how a TokenAssembly divides up a string.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowTokenAssembly {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {
		String s = "int i = 3;";
		TokenAssembly a = new TokenAssembly(s);
		while (a.hasNext()) {
			System.out.println(a.next());
		}
	}
}
