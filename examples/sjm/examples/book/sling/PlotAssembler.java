package sjm.examples.book.sling;

import sjm.parse.*;

/** 
 * Pops a function, and pushes an <code>AddFunctionCommand
 * </code> object. This command, when executed, will create
 * a renderable function. The renderable function includes 
 * the function that we pop now and the value of the "nLine"
 * variable.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class PlotAssembler implements IAssembler {
	/**
	 * Pop a function, and push a command that will, at 
	 * execution time, create a renderable function. The
	 * renderable function includes the popped function and
	 * the value of the "nLine" variable.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		SlingTarget target = (SlingTarget) a.getTarget();
		SlingFunction f = (SlingFunction) a.pop();
		a.push(new AddFunctionCommand(target.getRenderables(), f, target.nLine()));
	}
}
