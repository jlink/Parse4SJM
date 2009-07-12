package sjm.examples.book.sling;

import java.util.Hashtable;

/**  
 * This class holds a collection of variables.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Scope {
	protected Hashtable dictionary = new Hashtable();

	/**
	 * Returns true if a variable of the given name appears
	 * in this scope.
	 * 
	 * @param String the variable name
	 * 
	 * @return true, if a variable of the given name appears
	 *         in this scope.
	 */
	public boolean isDefined(String name) {
		return dictionary.containsKey(name);
	}

	/**
	 * Returns a variable of the given name from this scope.
	 *
	 * If the so-named variable is not already in this scope,
	 * the scope will create it and add the variable to itself.
	 * 
	 * @param String the variable name
	 * 
	 * @return a variable of the given name from this scope
	 */
	public Variable lookup(String name) {
		Variable v = (Variable) dictionary.get(name);
		if (v == null) {
			v = new Variable(name);
			dictionary.put(v.name, v);
		}
		return v;
	}
}
