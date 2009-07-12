package sjm.examples.book.imperative;

import sjm.examples.book.engine.*;

/**
 * This class shows a simple composition of commands from
 * <code>sjm.imperative</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCommand {
	/**
	 * Show a simple composition of commands from <code>
	 * sjm.imperative</code>.
	 */
	public static void main(String[] args) {
		Fact go = new Fact("go!");
		PrintlnCommand p = new PrintlnCommand(go);

		Variable i = new Variable("i");
		ForCommand f = new ForCommand(i, 1, 5, p);

		f.execute();
	}
}
