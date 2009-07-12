package sjm.parse;

import java.util.Set;

public interface IParser {

	/**
	 * Returns an assembly with the greatest possible number of elements
	 * consumed by matches of this parser.
	 * 
	 * @return an assembly with the greatest possible number of elements
	 *         consumed by this parser
	 * 
	 * @param Assembly
	 *            an assembly to match against
	 * 
	 */
	Assembly bestMatch(Assembly a);

	String getName();

	void setName(String name);

	Set<IParser> children();

	/**
	 * Return all children that are candidates for left recursion issues
	 */
	Set<IParser> leftChildren();

	boolean isTerminal();

	IParser setAssembler(IAssembler assembler);

	boolean isConstant();
}
