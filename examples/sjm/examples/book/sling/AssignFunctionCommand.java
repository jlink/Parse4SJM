package sjm.examples.book.sling;

import sjm.examples.book.imperative.Command;

/**
 * This command, when executed, evaluates a function and sets
 * it as the value of a variable.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class AssignFunctionCommand extends Command {
	protected Variable v;
	protected SlingFunction f;

	/**
	 * Construct a command to assign the supplied function to 
	 * the supplied variable.
	 *
	 * @param   Variable   the variable to assign to
	 *
	 * @param   SlingFunction   the function to evaluate and 
	 *                          assign to the variable at 
	 *                          execution time
	 */
	public AssignFunctionCommand(Variable v, SlingFunction f) {
		this.v = v;
		this.f = f;
	}

	/**
	 * Evaluate the function and assign the resulting, new function
	 * as the value of this object's variable.
	 */
	public void execute() {
		v.setValue(f.eval());
	}

	/**
	 * Returns a string description of this command.
	 *
	 * @return   a string description of this command
	 */
	public String toString() {
		return v + " = " + f;
	}
}
