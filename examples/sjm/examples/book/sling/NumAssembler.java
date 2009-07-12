package sjm.examples.book.sling;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/** 
 * Pops a number token, and pushes a number function.
 * <p>
 * A design principle for this package is that "everything" is 
 * a two-dimensional function of time. Thus if the stack 
 * contains, say, a token that is just the number 3, this 
 * assembler will push a new <code>Cartesian</code> function 
 * (t, 3). This allows, for example, a Sling statement like 
 * <code>plot 3;</code> to be meaningful. In this case, the 
 * Sling design principles will use 3 as the y component of a 
 * function, and will use time as the x component. The plot 
 * will be of a line segment from (0, 3) to (1, 3), since time 
 * varies from 0 to 1 during the course of a plot.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NumAssembler implements IAssembler {
	/**
	 * Pop a number n, and push the function (t, n).
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		Token t = (Token) a.pop();
		a.push(new Cartesian(new T(), new Point(0, t.nval())));
	}
}
