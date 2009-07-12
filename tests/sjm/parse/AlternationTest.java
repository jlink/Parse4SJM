package sjm.parse;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import sjm.parse.tokens.*;

public class AlternationTest extends AbstractParsingTest {

	Alternation alternation;

	@Before
	public void init() {
		alternation = new Alternation();
	}

	@Test
	public void noMatch() {
		alternation.add(new CaselessLiteral("abc"));
		assertNoMatch("def");
	}

	@Test
	public void fullMatch() {
		alternation.add(new CaselessLiteral("abc"));
		alternation.add(new Num());
		alternation.add(new Empty());
		assertCompleteMatch("abc");
		assertCompleteMatch("2.3");
		assertCompleteMatch("");
		assertNoCompleteMatch("def");
	}

	@Test
	public void children() {
		alternation.add(new CaselessLiteral("abc"));
		alternation.add(new Num());
		assertEquals(2, getParser().children().size());
	}

	@Test
	public void leftChildren() {
		alternation.add(new CaselessLiteral("abc"));
		alternation.add(new Num());
		assertEquals(2, getParser().leftChildren().size());
	}

	@Override
	protected Parser getParser() {
		return alternation;
	}
}
