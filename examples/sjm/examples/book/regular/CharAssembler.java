package sjm.examples.book.regular;

import sjm.parse.*;
import sjm.parse.chars.SpecificChar;

/**
 * Pop a <code>Character</code> from the stack and push a 
 * <code>SpecificChar</code> parser in its place.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class CharAssembler implements IAssembler {
	/**
	 * Pop a <code>Character</code> from the stack and push a 
	 * <code>SpecificChar</code> interpeter in its place.
	 * 
	 * @param   Assembly   the assembly whose stack to use
	 */
	public void workOn(Assembly a) {
		a.push(new SpecificChar((Character) a.pop()));
	}
}
