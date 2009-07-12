package sjm.examples.book.introduction;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show how to create a composite parser.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowComposite {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {

		Alternation adjective = new Alternation();
		adjective.add(new Literal("steaming"));
		adjective.add(new Literal("hot"));

		Sequence good = new Sequence();
		good.add(new Repetition(adjective));
		good.add(new Literal("coffee"));

		String s = "hot hot steaming hot coffee";
		Assembly a = new TokenAssembly(s);
		System.out.println(good.bestMatch(a));

	}
}
