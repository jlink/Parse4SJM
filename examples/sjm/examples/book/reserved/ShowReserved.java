package sjm.examples.book.reserved;

import java.util.*;

import sjm.parse.Assembly;
import sjm.parse.tokens.*;

/**
 * This class shows the use of a customized tokenizer, and the use of a terminal
 * that looks for the new token type.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowReserved {
	/**
	 * Show the use of a customized tokenizer, and the use of a terminal that
	 * looks for the new token type.
	 */
	public static void main(String[] args) {

		Tokenizer t = VolumeQuery2.tokenizer();
		t.setString("How many cups are in a gallon?");

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly(t));

		System.out.println(VolumeQuery2.query().match(v));
	}
}
