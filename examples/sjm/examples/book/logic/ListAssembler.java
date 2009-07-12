package sjm.examples.book.logic;

import java.util.List;

import sjm.examples.book.engine.*;
import sjm.parse.*;
import sjm.parse.tokens.Token;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops the terms of a list from an assembly's stack, builds the list, and
 * pushes it.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ListAssembler implements IAssembler {
	/**
	 * Pops the terms of a list from an assembly's stack, builds the list, and
	 * pushes it.
	 * <p>
	 * This method expects a series of terms to lie on top of a stack, with an
	 * open bracket token lying beneath. If there is no '[' marker, this class
	 * will throw an <code>EmptiStackException</code>.
	 * 
	 * @param Assembly
	 *            the assembly to work on
	 */
	public void workOn(Assembly a) {
		Token fence = new Token('[');
		List<Object> termVector = AssemblerHelper.elementsAbove(a, fence);
		Term[] termArray = StructureWithTermsAssembler.vectorReversedIntoTerms(termVector);

		if (termArray.length == 0) {
			a.push(Structure.emptyList);
		} else {
			a.push(Structure.list(termArray));
		}
	}
}
