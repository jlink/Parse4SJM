package sjm.examples.book.query;

import sjm.parse.PubliclyCloneable;
import sjm.parse.tokens.TokenTester;

/**
 * This class tests that class <code>Jaql</code> can parse
 * random language elements.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 *
 * @see JaqlUe
 */
public class JaqlTester extends TokenTester {
	protected static Speller speller;

	/**
	 * 
	 */
	public JaqlTester() {
		super(new JaqlParser(speller()).start());
	}

	/*
	 * A Jaql target is a <code>QueryBuilder</code>.
	 */
	protected PubliclyCloneable<? extends Object> freshTarget() {
		return new QueryBuilder(speller());
	}

	/**
	 * Run a test.
	 */
	public static void main(String[] args) {
		new JaqlTester().test();
	}

	/*
	 * Provide a spell that allows any class or variable name.
	 */
	protected static Speller speller() {
		if (speller == null) {
			speller = new MellowSpeller();
		}
		return speller;
	}
}
