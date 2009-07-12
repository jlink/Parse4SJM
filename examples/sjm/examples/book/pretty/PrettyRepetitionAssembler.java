package sjm.examples.book.pretty;

import java.util.List;

import sjm.parse.*;

/**
 * Replace the nodes above a given "fence" object with a new composite that
 * holds the popped nodes as its children.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class PrettyRepetitionAssembler implements IAssembler {
	protected String name;
	protected Object fence;

	/**
	 * Construct an assembler that will replace the nodes above the supplied
	 * "fence" object with a new composite that will hold the popped nodes as
	 * its children.
	 */
	public PrettyRepetitionAssembler(String name, Object fence) {
		this.name = name;
		this.fence = fence;
	}

	/**
	 * Replace the nodes above a given "fence" object with a new composite that
	 * holds the popped nodes as its children.
	 * 
	 * @param Assembly
	 *            the assembly to work on
	 */
	public void workOn(Assembly a) {
		CompositeNode newNode = new CompositeNode(name);
		List<Object> v = AssemblerHelper.elementsAbove(a, fence);
		for (Object each : v) {
			newNode.add((ComponentNode) each);

		}
		a.push(newNode);
	}
}
