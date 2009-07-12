package sjm.examples.book.introduction;

import sjm.parse.tokens.TokenAssembly;

/**
 * Show how an assembly prints itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAssemblyAppearance {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {

		String s1 = "Congress admitted Colorado in 1876.";
		System.out.println(new TokenAssembly(s1));

		String s2 = "admitted(colorado, 1876)";
		System.out.println(new TokenAssembly(s2));
	}
}
