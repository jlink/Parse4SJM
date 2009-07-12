package sjm.parse;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import sjm.parse.tokens.Literal;

public class LiteralTest extends AbstractParsingTest {

	Literal literal;

	@Before
	public void init() {
		literal = new Literal("hello");
	}

	@Test
	public void noMatch() {
		assertNoMatch("abc");
		assertNoMatch("HELLO");
	}

	@Test
	public void match() {
		assertCompleteMatch("hello");
	}

	@Test
	public void noChildren() {
		assertTrue(getParser().children().isEmpty());
	}

	@Override
	protected Parser getParser() {
		return literal;
	}
}
