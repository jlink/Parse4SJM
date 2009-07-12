package sjm.examples.book.coffee;

import sjm.parse.*;

/**
 * This assembler sets a target coffee object boolean that
 * indicates the type of coffee also comes in a french
 * roast.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AlsoFrenchAssembler implements IAssembler {
	/** 
	 * Set a target coffee object's boolean to indicate that this 
	 * type of coffee also comes in a french roast.
	 *
	 * @param  Assembly  the assembly to work on
	 */
	public void workOn(Assembly a) {
		Coffee c = (Coffee) a.getTarget();
		c.setAlsoOfferFrench(true);
	}
}
