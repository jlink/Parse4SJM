package sjm.examples.book.mechanics;

import java.util.*;

import sjm.examples.book.arithmetic.ArithmeticParser;
import sjm.parse.Assembly;
import sjm.parse.tokens.TokenAssembly;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows the complete results of matching an arithmetic expression
 * parser against an expression.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowSequenceLeftovers {
	/**
	 * Show the complete results of matching an arithmetic expression parser
	 * against an expression.
	 */
	public static void main(String args[]) {

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly("3 * 4 + 5"));

		System.out.println(ArithmeticParser.start().match(v));
	}
}
