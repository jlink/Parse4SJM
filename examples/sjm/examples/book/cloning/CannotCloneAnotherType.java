package sjm.examples.book.cloning;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class will not compile; it just shows that an object
 * cannot send clone() to another type of object.
 *
 * @author Steven J. Metsker
 * @version 1.0
 */
public class CannotCloneAnotherType {
	/**
	 * Just a demo, this will not compile.
	 */
	public static void main(String args[]) {
		CannotCloneAnotherType ccat = new CannotCloneAnotherType();

		try {
			ccat.clone(); // this would be Ok.
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
		}

		Integer i = new Integer(42);
		System.out.println(i);
		//i.clone(); // will not compile!
	}
}
