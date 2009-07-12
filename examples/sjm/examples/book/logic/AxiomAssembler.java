package sjm.examples.book.logic;

import java.util.Stack;

import sjm.examples.book.engine.*;
import sjm.parse.*;

/**
 * Pops the structures of a rule from an assembly's stack, 
 * and constructs and pushes a rule.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AxiomAssembler implements IAssembler {
	/**
	 * Pops all of the structures on the stack, builds a rule
	 * from them, and pushes it.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		Stack<? extends Object> s = a.getStack();
		Structure[] sa = new Structure[s.size()];
		for (int i = 0; i < s.size(); i++) {
			sa[i] = (Structure) s.elementAt(i);
		}
		s.removeAllElements();
		a.push(new Rule(sa));
	}
}
