package sjm.parse;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import sjm.parse.tokens.*;

public class TokenizerTest {

	Tokenizer tokenizer;

	@Test
	public void eof() throws IOException {
		tokenizer = new Tokenizer("");
		assertNextToken(Token.EOF);
		tokenizer = new Tokenizer("a");
		tokenizer.nextToken();
		assertEOF();
	}

	@Test
	public void num() throws IOException {
		tokenizer = new Tokenizer("1.0");
		assertNextToken(new BigDecimal("1.0"));
	}

	@Test
	public void moreNums() throws IOException {
		tokenizer = new Tokenizer(
				"1 1.00 3.141592653589793238462643383279 1000000000000");
		assertNextToken(new BigDecimal("1"));
		assertNextToken(new BigDecimal("1.00"));
		assertNextToken(new BigDecimal("3.141592653589793238462643383279"));
		assertNextToken(new BigDecimal("1000000000000"));
	}

	@Test
	public void symbol() throws IOException {
		tokenizer = new Tokenizer("! <= : .");
		assertNextSymbol("!");
		assertNextSymbol("<=");
		assertNextSymbol(":");
		assertNextSymbol(".");
	}

	@Test
	public void word() throws IOException {
		tokenizer = new Tokenizer("those  are my      words    ");
		assertNextWord("those");
		assertNextWord("are");
		assertNextWord("my");
		assertNextWord("words");
		assertEOF();
	}

	@Test
	public void mixedWords() throws IOException {
		tokenizer = new Tokenizer("those123 a456b 12abc");
		assertNextWord("those123");
		assertNextWord("a456b");
		assertNextToken(new BigDecimal("12"));
		assertNextWord("abc");
		assertEOF();
	}

	@Test
	public void quoted() throws IOException {
		tokenizer = new Tokenizer("\"a quote from !hell!\"");
		Token nextToken = tokenizer.nextToken();
		assertTrue(nextToken.isQuotedString());
		assertEquals("\"a quote from !hell!\"", nextToken.value());
	}

	@Test
	public void unicodeChars() throws IOException {
		tokenizer = new Tokenizer("\uD840llo h\uD841llo");
		tokenizer.setCharacterState(0xd840, 0xd841, tokenizer.wordState());
		tokenizer.wordState().setWordChars(0xd840, 0xd841, true);
		assertNextWord("\uD840llo");
		assertNextWord("h\uD841llo");
	}
	
	@Test
	public void javaStyleComments() throws Exception {
		tokenizer = new Tokenizer("hallo // this is a comment");
		assertNextWord("hallo");
		assertEOF();
		tokenizer = new Tokenizer("hallo /*this is a comment*/ man");
		assertNextWord("hallo");
		assertNextWord("man");
	}

	@Test
	public void disableJavaStyleComments() throws Exception {
		tokenizer = new Tokenizer("hallo // comment /*comment*/");
		tokenizer.disableComments();
		assertNextWord("hallo");
		assertNextSymbol("/");
		assertNextSymbol("/");
		assertNextWord("comment");
		assertNextSymbol("/");
		assertNextSymbol("*");
		assertNextWord("comment");
		assertNextSymbol("*");
		assertNextSymbol("/");
		assertEOF();
	}

	@Test
	public void whitespaceTokenizing() throws IOException {
		tokenizer = new Tokenizer("a b  c\t\nd ");
		tokenizer.enableWhitespaceTokenizing();
		assertNextWord("a");
		assertNextWhitespace(" ");
		assertNextWord("b");
		assertNextWhitespace("  ");
		assertNextWord("c");
		assertNextWhitespace("\t\n");
		assertNextWord("d");
		assertNextWhitespace(" ");
		assertEOF();
	}

	private void assertEOF() throws IOException {
		assertEquals(Token.EOF, tokenizer.nextToken());
	}

	private void assertNextWhitespace(String whitespace) throws IOException {
		Token nextToken = tokenizer.nextToken();
		assertTrue("should be whitespace", nextToken.isWhitespace());
		assertEquals(whitespace, nextToken.value());
	}

	private void assertNextWord(String symbol) throws IOException {
		Token nextToken = tokenizer.nextToken();
		assertTrue("should be word", nextToken.isWord());
		assertEquals(symbol, nextToken.value());
	}

	private void assertNextSymbol(String symbol) throws IOException {
		Token nextToken = tokenizer.nextToken();
		assertTrue("should be symbol", nextToken.isSymbol());
		assertEquals(symbol, nextToken.value());
	}

	private void assertNextToken(Object expected) throws IOException {
		assertEquals(expected, tokenizer.nextToken().value());
	}

}
