package sjm.examples.book.cloning;

import sjm.parse.PubliclyCloneable;

/** 
 * This class just supports the <code>ThisClass</code>
 * example of a typical clone.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Professor implements PubliclyCloneable<Professor> {
	/**
	 * Return a copy of this object.
	 *
	 * @return a copy of this object
	 */
	public Professor clone() {
		try {
			return (Professor) super.clone();
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}
}
