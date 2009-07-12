package sjm.examples.book.pretty;

import sjm.parse.*;

/**
 * Replace a <code>ComponentNode</code> object on the stack
 * with a new composite that holds the popped node as its
 * only child.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyAlternationAssembler implements IAssembler {
	protected String name;

	/**
	 * Create an assembler that will replace a <code>ComponentNode
	 * </code> object on the stack with a new composite that holds 
	 * the popped node as its only child and whose name is as
	 * supplied here.
	 */
	public PrettyAlternationAssembler(String name) {
		this.name = name;
	}

	/**
	 * Replace a <code>ComponentNode</code> object on the stack
	 * with a new composite that holds the popped node as its
	 * only child.
	 *
	 * @param   Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		CompositeNode newNode = new CompositeNode(name);
		ComponentNode node = (ComponentNode) a.pop();
		newNode.insert(node);
		a.push(newNode);
	}
}
