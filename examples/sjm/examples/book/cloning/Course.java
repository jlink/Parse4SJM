package sjm.examples.book.cloning;

import sjm.parse.PubliclyCloneable;

/**  
 * This class shows a typical clone() method.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Course implements PubliclyCloneable<Course> {
	protected Professor professor;
	protected Textbook textbook;

	/**
	 * Return a copy of this object.
	 *
	 * @return a copy of this object
	 */
	public Course clone() {
		try {
			Course copy = (Course) super.clone();
			copy.setProfessor(professor.clone());
			copy.setTextbook(textbook.clone());
			return copy;
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}

	/**
	 * Get the professor.
	 *
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}

	/**
	 * Get the textbook.
	 *
	 * @return the textbook
	 */
	public Textbook getTextbook() {
		return textbook;
	}

	/**
	 * Set the professor.
	 *
	 * @param   Professor   professor
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	/**
	 * Set the textbook.
	 *
	 * @param   Textbook   textbook
	 */
	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}
}
