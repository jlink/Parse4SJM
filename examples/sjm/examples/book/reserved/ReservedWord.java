package sjm.examples.book.reserved;

import java.util.Vector;

import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * A ReservedWord matches a word whose token type is 
 * <code>WordOrReservedState.TT_RESERVED</code>.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ReservedWord extends Terminal {

	/**
	 * Returns true if an assembly's next element is a reserved 
	 * word.
	 *
	 * @param   object   an element from an assembly
	 *
	 * @return   true, if an assembly's next element is a 
	 *           reserved word
	 */
	protected boolean qualifies(Object o) {
		Token t = (Token) o;
		return t.ttype() == WordOrReservedState.TT_RESERVED;
	}

	/**
	 * Returns a textual description of this parser.
	 *
	 * @param   vector   a list of parsers already printed in this
	 *                   description
	 * 
	 * @return   string   a textual description of this parser
	 *
	 * @see Parser#toString()
	 */
	public String unvisitedString(Vector visited) {
		return "ReservedWord";
	}
}
