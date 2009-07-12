package sjm.examples.book.introduction;

import sjm.parse.tokens.TokenAssembly;

/**
 * Show what counts as a number.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowNums {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {
		String s = "12 12.34 .1234 1234e-2";
		TokenAssembly a = new TokenAssembly(s);
		while (a.hasNext()) {
			System.out.println(a.next());
		}
	}
}
