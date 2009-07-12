package sjm.parse;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.*;

import sjm.parse.tokens.Int;

public class IntTest extends AbstractParsingTest {

	Int intTerminal;

	@Before
	public void init() {
		intTerminal = new Int();
	}

	@Test
	public void noMatch() {
		assertNoMatch("abc");
		assertNoMatch("1.23");
	}

	@Test
	public void match() {
		Assembly result = completeMatch("1000");
		assertEquals(new BigInteger("1000"), popValueFromAssembly(result));
	}

	@Test
	public void noChildren() {
		assertTrue(getParser().children().isEmpty());
	}

	@Override
	protected Parser getParser() {
		return intTerminal;
	}
}
