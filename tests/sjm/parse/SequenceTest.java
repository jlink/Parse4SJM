package sjm.parse;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.*;

import sjm.parse.tokens.*;

public class SequenceTest extends AbstractParsingTest {

	Sequence sequence;

	@Before
	public void init() {
		sequence = new Sequence();
	}

	@Test
	public void noMatch() {
		sequence.add(new CaselessLiteral("abc"));
		assertNoMatch("def");
	}

	@Test
	public void fullMatch() {
		sequence.add(new CaselessLiteral("abc"));
		assertCompleteMatch("abc");

		sequence.add(new Num());
		Assembly result = completeMatch("abc 1.0");
		assertEquals(new BigDecimal("1.0"), popValueFromAssembly(result));
	}

	@Test
	public void partialMatch() {
		sequence.add(new CaselessLiteral("abc"));
		Assembly result = bestMatch("abc def");
		assertEquals(1, result.elementsRemaining());
		assertEquals(1, result.elementsConsumed());
		assertEquals("abc", popValueFromAssembly(result));
	}

	@Test
	public void children() {
		sequence.add(new CaselessLiteral("abc"));
		sequence.add(new Num());
		assertEquals(2, getParser().children().size());
	}

	@Test
	public void leftChildren() {
		sequence.add(new CaselessLiteral("abc"));
		sequence.add(new Num());
		assertEquals(1, getParser().leftChildren().size());
		assertTrue("" + getParser().leftChildren(), getParser().leftChildren().contains(new CaselessLiteral("abc")));
	}

	@Override
	protected Parser getParser() {
		return sequence;
	}
}
