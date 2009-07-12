package sjm.examples.book.sling;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class tests that class <code>SlingParser</code> can
 * parse random language elements.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class SlingTester extends TokenTester {
	/**
	 * Create a tester for the primary Sling parser.
	 */
	public SlingTester() {
		super(new SlingParser().statement());
	}

	/*
	 * Return an assembly for a given string, using the Sling
	 * tokenizer.
	 */
	protected Assembly assembly(String s) {
		Tokenizer t = new SlingParser().tokenizer();
		t.setString(s);
		return new TokenAssembly(t);
	}

	/*
	 * The Sling parser expects a SlingTarget object as an
	 * assembly's target. Normally, this target expects two
	 * sliders to be available, but we send null's here since
	 * there is no GUI in use during random testing.
	 */
	protected PubliclyCloneable<? extends Object> freshTarget() {
		return new SlingTarget(null, null);
	}

	/**
	 * Run a test.
	 */
	public static void main(String[] args) {
		new SlingTester().test();
	}
}
