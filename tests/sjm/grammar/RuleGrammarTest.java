package sjm.grammar;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

public class RuleGrammarTest {

	private Grammar targetGrammar;
	private RuleGrammar grammar;

	@Before
	public void init() {
		targetGrammar = new Grammar("test");
		grammar = new RuleGrammar(targetGrammar);
	}

	@Test
	public void emptyRuleFails() {
		IParsingResult result = grammar.parse("r = ");
		assertFalse(result.isCompleteMatch());
	}

	@Test
	public void caselessLiteralDefinition() {
		IParser rule = resultRule("\"cl\"");
		assertEquals(new CaselessLiteral("cl"), rule);
	}

	@Test
	public void resultStackShouldHaveRuleName() {
		IParsingResult result = grammar.parse("myRule = " + "\"cl\"");
		assertEquals(result.getStack().peek(), "myRule");
	}

	@Test
	public void symbolDefinition() {
		IParser rule = resultRule("'<'");
		assertEquals(new Symbol("<"), rule);
	}

	@Test
	public void ruleCanEndWithSemicolon() {
		assertTrue(grammar.parse("r = '<';").isCompleteMatch());
	}

	@Test
	public void additionalWhitespaceWillBeIgnored() {
		assertTrue(grammar.parse("  aRule  =  '<' ;   ").isCompleteMatch());
		assertEquals(new Symbol("<"), targetGrammar.getRule("aRule"));
	}

	@Test
	public void reference() {
		IParser rule = resultRule("other");
		assertEquals(new RuleReference("other", targetGrammar), rule);
	}

	@Test(expected = GrammarException.class)
	public void unknownTerminalType() {
		grammar.parse("r = UnknownXYZ");
	}

	@Test
	public void builtInTerminalTypes() {
		Sequence seq = (Sequence) resultRule("Num Int Word QuotedString");
		assertTrue(seq.getSubparsers().get(0) instanceof Num);
		assertTrue(seq.getSubparsers().get(1) instanceof Int);
		assertTrue(seq.getSubparsers().get(2) instanceof Word);
		assertTrue(seq.getSubparsers().get(3) instanceof QuotedString);
	}

	@Test
	public void selfMadeTerminalTypes() {
		targetGrammar.registerTerminal(UpperCaseWord.class);
		targetGrammar.registerTerminal("UCW", UpperCaseWord.class);
		Sequence seq = (Sequence) resultRule("UpperCaseWord UCW");
		assertTrue(seq.getSubparsers().get(0) instanceof UpperCaseWord);
		assertTrue(seq.getSubparsers().get(1) instanceof UpperCaseWord);
	}

	@Test
	public void sequence() {
		Sequence seq = (Sequence) resultRule("\"a\" '<' \"b\"");
		assertEquals(new CaselessLiteral("a"), seq.getSubparsers().get(0));
		assertEquals(new Symbol("<"), seq.getSubparsers().get(1));
		assertEquals(new CaselessLiteral("b"), seq.getSubparsers().get(2));
	}

	@Test
	public void repetition() {
		Repetition rep = (Repetition) resultRule("a*");
		assertEquals(new RuleReference("a", targetGrammar), rep.getSubparser());
		assertEquals(0, rep.requiredMatches());
		assertTrue(grammar.parse("r = \"a\"*").isCompleteMatch());
		assertTrue(grammar.parse("r = '<' *").isCompleteMatch());
	}

	@Test
	public void alternation() {
		Alternation alt = (Alternation) resultRule("'<' | '>'");
		assertEquals(new Symbol("<"), alt.getSubparsers().get(0));
		assertEquals(new Symbol(">"), alt.getSubparsers().get(1));

		assertTrue(grammar.parse("r = \"a\" | b | '*'").isCompleteMatch());

		alt = (Alternation) resultRule("a* | '+' '*'");
		assertTrue(alt.getSubparsers().get(0) instanceof Repetition);
		assertTrue(alt.getSubparsers().get(1) instanceof Sequence);
	}

	@Test
	public void parenthesesForUnnestedElements() {
		RuleReference ref = (RuleReference) resultRule("(a)");
		assertEquals(new RuleReference("a", targetGrammar), ref);

		assertParserType("(a b c)", Sequence.class);
		assertParserType("(a*)", Repetition.class);
		assertParserType("(a|b)", Alternation.class);
	}

	@Test
	public void nestedParentheses() {
		assertParserType("(a|b) c", Sequence.class);
		assertParserType("a (b c)*", Sequence.class);
		assertParserType("(a b) | (c d)*", Alternation.class);
		assertParserType("(a b c d)*", Repetition.class);
		assertParserType("(a b c d)+", Repetition.class);
		printGrammar(targetGrammar);
	}

	@Test
	public void byDefaultConstantsAreNotDiscarded() {
		Sequence ref = (Sequence) resultRule("'<' \"a\"");
		assertFalse(((Terminal) ref.getChild(0)).isDiscarded());
		assertFalse(((Terminal) ref.getChild(1)).isDiscarded());
	}

	@Test
	public void switchOnDefaultDiscardOfConstants() {
		targetGrammar.discardAllConstants();
		Sequence ref = (Sequence) resultRule("'<' \"a\"");
		assertTrue(((Terminal) ref.getChild(0)).isDiscarded());
		assertTrue(((Terminal) ref.getChild(1)).isDiscarded());
	}

	@Test
	public void explicitConstantsDiscard() {
		Sequence ref = (Sequence) resultRule("#'<' #\"a\"");
		assertTrue(((Terminal) ref.getChild(0)).isDiscarded());
		assertTrue(((Terminal) ref.getChild(1)).isDiscarded());
	}

	@Test
	public void atLeastOne() {
		Repetition rep = (Repetition) resultRule("a+");
		assertEquals(new RuleReference("a", targetGrammar), rep.getSubparser());
		assertEquals(1, rep.requiredMatches());
		assertTrue(grammar.parse("r = \"a\"+").isCompleteMatch());
		assertTrue(grammar.parse("r = '<' +").isCompleteMatch());
	}

	@Test
	public void printing() {
		printGrammar(grammar);
	}

	private void printGrammar(Grammar grammar) {
		StringWriter sw = new StringWriter();
		grammar.printOn(new PrintWriter(sw));
		System.out.println(sw.toString());
	}

	private void assertParserType(String body, Class<? extends IParser> parserType) {
		IParser parser = resultRule(body);
		assertTrue("should be of type: " + parserType, parserType.isInstance(parser));
	}

	private IParser resultRule(String ruleBody) {
		IParsingResult result = grammar.parse("r = " + ruleBody);
		assertTrue("should be complete match: " + result, result.isCompleteMatch());
		IParser rule = targetGrammar.getRule("r");
		return rule;
	}
}
