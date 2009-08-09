package sjm.grammar;

import static org.junit.Assert.fail;

import org.junit.*;

public class LeftRecursionCheckerTest {

	private Grammar grammar;

	@Before
	public void init() {
		grammar = new Grammar("test");
	}

	@Test
	public void noRecursion() {
		grammar.defineRule("s = '<' '+'");
		assertNoLeftRecursionException();
	}

	@Test
	public void rightRecursionIsFine() {
		grammar.defineRule("s = '<' s");
		assertNoLeftRecursionException();
	}

	@Test
	public void ruleReferencesItself() {
		grammar.defineRule("s = s");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInSequence() {
		grammar.defineRule("s = s '>'");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInRepetition() {
		grammar.defineRule("s = (s '>')*");
		assertLeftRecursionException();

		grammar.defineRule("s = (s '>')+");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInAlternation() {
		grammar.defineRule("s = ('>' s) | s");
		assertLeftRecursionException();

		grammar.defineRule("s = (s '>') | '<'");
		assertLeftRecursionException();
	}

	@Test
	public void indirectLeftRecursion() {
		grammar.defineRule("s = a '<'");
		grammar.defineRule("a = Num | s '+'");
		assertLeftRecursionException();
	}

	@Test
	public void complexExample() {
		grammar.defineRule("s = a '<'");
		grammar.defineRule("a = Num | b '+'");
		grammar.defineRule("b = Num | (c '+') | ('=' d)");
		grammar.defineRule("c = Int | ('+' c) | d");
		grammar.defineRule("d = s+");
		assertLeftRecursionException();

		grammar.defineRule("d = Word");
		assertNoLeftRecursionException();
	}

	private void assertNoLeftRecursionException() {
		new LeftRecursionChecker(grammar).check();
	}

	private void assertLeftRecursionException() {
		try {
			new LeftRecursionChecker(grammar).check();
			fail("Left Recursion expected");
		} catch (GrammarException expected) {
		}
	}

}
