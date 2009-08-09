package sjm.parse;

import static org.junit.Assert.assertEquals;

import java.math.*;
import java.util.List;

import org.junit.Test;

import sjm.parse.tokens.Token;

public class TokenAsTypeTest {

	private Token token;

	@Test
	public void word() {
		token = new Token("hello");
		assertEquals("hello", token.asType(String.class));
		assertEquals(BigDecimal.ZERO, token.asType(Number.class));
		assertEquals(BigDecimal.ZERO, token.asType(BigDecimal.class));
		assertEquals(BigInteger.ZERO, token.asType(BigInteger.class));
		assertEquals(new Float(0.0), token.asType(Float.class));
		assertEquals(new Double(0.0), token.asType(Double.class));
		assertEquals(new Long(0), token.asType(Long.class));
	}

	@Test
	public void number() {
		token = new Token(new BigDecimal("1.5"));
		assertEquals("1.5", token.asType(String.class));
		assertEquals(new BigDecimal("1.5"), token.asType(Number.class));
		assertEquals(new BigDecimal("1.5"), token.asType(BigDecimal.class));
		assertEquals(BigInteger.ONE, token.asType(BigInteger.class));
		assertEquals(new Float(1.5), token.asType(Float.class));
		assertEquals(new Double(1.5), token.asType(Double.class));
		assertEquals(new Long(1), token.asType(Long.class));
	}

	@Test(expected = RuntimeException.class)
	public void asUnknownType() {
		token = new Token("hello");
		token.asType(List.class);
	}

}
