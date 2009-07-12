package sjm.examples.book.query;

import java.util.*;

import sjm.examples.book.engine.*;

/**
 * This class maintains dictionaries of the proper spelling of class and
 * variable names in the chip object model.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ChipSpeller implements Speller {
	protected Dictionary classNames;
	protected Dictionary variableNames;

	/**
	 * Initialize the ChipSpeller.
	 */
	public ChipSpeller() {
		loadClassNames();
		loadVariableNames();
	}

	/*
	 * Add one class name.
	 */
	protected void addClassName(String s) {
		classNames.put(s.toLowerCase(), s);
	}

	/*
	 * Add one variable name.
	 */
	protected void addVariableName(String s) {
		variableNames.put(s.toLowerCase(), s);
	}

	/**
	 * Return the properly capitalized spelling of a class name, given any
	 * capitalization of the name.
	 * 
	 * @return the properly capitalized spelling of a class name, given any
	 *         capitalization of the name.
	 */
	public String getClassName(String s) {
		return (String) classNames.get(s.toLowerCase());
	}

	/**
	 * Return the properly capitalized spelling of a variable name, given any
	 * capitalization of the name.
	 * 
	 * @return the properly capitalized spelling of a variable name, given any
	 *         capitalization of the name.
	 */
	public String getVariableName(String s) {
		return (String) variableNames.get(s.toLowerCase());
	}

	/*
	 * Load all the class names from ChipData into the class name dictionary.
	 */
	protected void loadClassNames() {
		classNames = new Hashtable();
		addClassName("chip");
		addClassName("customer");
		addClassName("order");
	}

	/*
	 * Load all the variable names from ChipData into the class name dictionary.
	 */
	protected void loadVariableNames() {
		variableNames = new Hashtable();
		/*
		 * Use the query templates to detect the variable names.
		 */
		Enumeration e = ChipSource.queries().elements();
		while (e.hasMoreElements()) {
			Structure s = (Structure) e.nextElement();
			Unification u = s.variables();
			/*
			 * Add each variable from each query template
			 */
			Iterator<Variable> e2 = u.iterator();
			while (e2.hasNext()) {
				Variable v = e2.next();
				addVariableName(v.name);
			}
		}
	}
}
