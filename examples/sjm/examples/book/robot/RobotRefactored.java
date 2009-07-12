package sjm.examples.book.robot;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Provide an example of a class that affords a parser for 
 * the "robot" command language. This class is a refactored 
 * version of the <code>RobotMonolithic</code> class, with
 * one method for each subparser in the robot language.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class RobotRefactored {
	public IParser command() {
		Alternation a = new Alternation();
		a.add(pickCommand());
		a.add(placeCommand());
		a.add(scanCommand());
		return a;
	}

	protected Parser location() {
		return new Word();
	}

	protected Parser pickCommand() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("pick"));
		s.add(new CaselessLiteral("carrier"));
		s.add(new CaselessLiteral("from"));
		s.add(location());
		return s;
	}

	protected Parser placeCommand() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("place"));
		s.add(new CaselessLiteral("carrier"));
		s.add(new CaselessLiteral("at"));
		s.add(location());
		return s;
	}

	protected Parser scanCommand() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("scan"));
		s.add(location());
		return s;
	}
}
