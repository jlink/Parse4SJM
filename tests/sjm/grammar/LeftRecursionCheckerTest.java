package sjm.grammar;

import static org.junit.Assert.*;

import org.junit.*;

public class LeftRecursionCheckerTest {

	private Grammar grammar;

	@Before
	public void init() {
		grammar = new Grammar("test");
	}

	@Test
	public void noRecursion() {
		grammar.addTextualRule("s = '<' '+'");
		assertNoLeftRecursionException();
	}

	@Test
	public void rightRecursionIsFine() {
		grammar.addTextualRule("s = '<' s");
		assertNoLeftRecursionException();
	}

	@Test
	public void ruleReferencesItself() {
		grammar.addTextualRule("s = s");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInSequence() {
		grammar.addTextualRule("s = s '>'");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInRepetition() {
		grammar.addTextualRule("s = (s '>')*");
		assertLeftRecursionException();

		grammar.addTextualRule("s = (s '>')+");
		assertLeftRecursionException();
	}

	@Test
	public void immediateLeftRecursionInAlternation() {
		grammar.addTextualRule("s = ('>' s) | s");
		assertLeftRecursionException();

		grammar.addTextualRule("s = (s '>') | '<'");
		assertLeftRecursionException();
	}

	@Test
	public void indirectLeftRecursion() {
		grammar.addTextualRule("s = a '<'");
		grammar.addTextualRule("a = Num | s '+'");
		assertLeftRecursionException();
	}

	@Test
	public void complexExample() {
		grammar.addTextualRule("s = a '<'");
		grammar.addTextualRule("a = Num | b '+'");
		grammar.addTextualRule("b = Num | (c '+') | ('=' d)");
		grammar.addTextualRule("c = Int | ('+' c) | d");
		grammar.addTextualRule("d = s+");
		assertLeftRecursionException();

		grammar.addTextualRule("d = Word");
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
