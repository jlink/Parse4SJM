package sjm.examples.book.logic;

import java.util.List;

import sjm.examples.book.engine.*;
import sjm.parse.*;
import sjm.parse.tokens.Token;

/**
 * Pops the terms and functor of a structure from an assembly's
 * stack, builds a structure, and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class StructureWithTermsAssembler implements IAssembler {
	/**
	 * Reverse a vector into an array of terms.
	 * 
	 * @param   Vector   the vector to reverse
	 *
	 * @return   Term[]   the vector, reversed
	 */
	public static Term[] vectorReversedIntoTerms(List<Object> v) {
		int size = v.size();
		Term[] terms = new Term[size];
		for (int i = 0; i < size; i++) {
			terms[size - 1 - i] = (Term) v.get(i);
		}
		return terms;
	}

	/**
	 * Pops the terms and functor of a structure from an assembly's
	 * stack, builds a structure, and pushes it.
	 * <p>
	 * This method expects a series of terms to lie on top of a 
	 * stack, with an open paren token lying underneath. If there 
	 * is no '(' marker, this class will throw an <code>
	 * EmptyStackException</code>.
	 * <p>
	 * Beneath the terms of the structure, this method expects to 
	 * find a token whose value is the functor of the structure.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		List<Object> termVector = AssemblerHelper.elementsAbove(a, new Token('('));
		Term[] termArray = vectorReversedIntoTerms(termVector);
		Token t = (Token) a.pop();
		a.push(new Structure(t.value(), termArray));
	}
}
