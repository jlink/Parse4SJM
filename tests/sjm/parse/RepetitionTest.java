package sjm.parse;

import static org.junit.Assert.*;

import org.junit.*;

import sjm.parse.tokens.*;

public class RepetitionTest extends AbstractParsingTest {

	Repetition repetition;

	@Before
	public void init() {
		repetition = new Repetition(new Literal("abc"));
	}

	@Test
	public void noMatch() {
		assertNoCompleteMatch("def");
	}

	@Test
	public void fullMatch() {
		// assertCompleteMatch("abc");
		assertCompleteMatch("");
		// assertCompleteMatch("abc abc abc abc");
	}

	@Test
	public void numberOfRequiredMatches() {
		repetition.requireMatches(2);
		assertEquals(2, repetition.requiredMatches());
		assertNoCompleteMatch("");
		assertNoCompleteMatch("abc");
		assertCompleteMatch("abc abc");
		assertCompleteMatch("abc abc abc abc");
	}

	@Test
	public void children() {
		repetition = new Repetition(new Num());
		assertEquals(1, getParser().children().size());
	}

	@Test
	public void leftChildren() {
		Num numChild = new Num();
		repetition = new Repetition(numChild);
		assertEquals(1, getParser().leftChildren().size());
		assertTrue(getParser().leftChildren().contains(numChild));
	}

	@Override
	protected Parser getParser() {
		return repetition;
	}
}
