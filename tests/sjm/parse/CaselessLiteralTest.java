package sjm.parse;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import sjm.parse.tokens.CaselessLiteral;

public class CaselessLiteralTest extends AbstractParsingTest {

	CaselessLiteral caselessLiteral;

	@Before
	public void init() {
		caselessLiteral = new CaselessLiteral("hello");
	}

	@Test
	public void noMatch() {
		assertNoMatch("abc");
	}

	@Test
	public void match() {
		assertCompleteMatch("hello");
		assertCompleteMatch("HELLO");
	}

	@Test
	public void noChildren() {
		assertTrue(getParser().children().isEmpty());
	}

	@Override
	protected Parser getParser() {
		return caselessLiteral;
	}
}
