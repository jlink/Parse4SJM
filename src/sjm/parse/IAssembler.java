package sjm.parse;

public interface IAssembler {

	/**
	 * This is the one method all subclasses must implement. It specifies what
	 * to do when a parser successfully matches against a assembly.
	 * 
	 * @param Assembly
	 *            the assembly to work on
	 */
	void workOn(Assembly a);

}