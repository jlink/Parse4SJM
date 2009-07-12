package sjm.parse;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import sjm.parse.tokens.*;

public class PopAllMatchesTest extends AbstractParsingTest {

	private Parser parser;
	private Assembly result;

	@Test
	public void individualParsers() {
		parser = new Empty();
		result = bestMatch("");
		assertTrue(result.popAllMatches().isEmpty());

		parser = new Word();
		result = bestMatch("allo");
		assertEquals(1, result.popAllMatches().size());

		parser = new Repetition(new Word());
		result = bestMatch("a b c d");
		List<Object> allMatches = result.popAllMatches();
		assertEquals(4, allMatches.size());
		assertEquals(new Token("a"), allMatches.get(0));
		assertEquals(new Token("d"), allMatches.get(3));

		parser = new Sequence().add(new Literal("abc")).add(new Word()).add(new Symbol(",").discard());
		result = bestMatch("abc hello,");
		allMatches = result.popAllMatches();
		assertEquals(2, allMatches.size());
		assertEquals(new Token("abc"), allMatches.get(0));
		assertEquals(new Token("hello"), allMatches.get(1));

		parser = new Alternation().add(new Literal("abc")).add(new Literal("def"));
		result = bestMatch("abc");
		allMatches = result.popAllMatches();
		assertEquals(1, allMatches.size());
		assertEquals(new Token("abc"), allMatches.get(0));
	}

	@Test
	public void stackManipulatedByAssemblers() {
		IAssembler deleteAssembler = new IAssembler() {
			public void workOn(Assembly a) {
				a.pop();
			}
		};
		IAssembler changeToStringAssembler = new IAssembler() {
			public void workOn(Assembly a) {
				a.push(((Token) a.pop()).sval());
			}
		};
		parser = new Sequence().add(new Literal("abc").setAssembler(deleteAssembler)).add(new Word().setAssembler(changeToStringAssembler)).add(new Symbol(",").discard());
		result = bestMatch("abc hello,");
		List<Object> allMatches = result.popAllMatches();
		assertEquals(1, allMatches.size());
		assertEquals("hello", allMatches.get(0));
	}

	@Test
	public void nestedParsers() {
		IAssembler changeToStringAssembler = new IAssembler() {
			public void workOn(Assembly a) {
				a.push(((Token) a.pop()).sval());
			}
		};
		Parser nested = new Repetition(new Word().setAssembler(changeToStringAssembler)).setAssembler(new IAssembler() {
			public void workOn(Assembly a) {
				List<Object> list = a.popAllMatches();
				a.push(list);
			}
		});
		parser = new Sequence().add(new Literal("list")).add(new Symbol("(")).add(nested).add(new Symbol(")").discard());
		result = bestMatch("list (a b c d)");
		List<Object> allMatches = result.popAllMatches();
		assertEquals(3, allMatches.size());
		List<Object> list = (List<Object>) allMatches.get(2);
		assertEquals(4, list.size());
	}

	@Test
	public void commaSeparatedListInBrackets() {
		IAssembler changeToStringAssembler = new IAssembler() {
			public void workOn(Assembly a) {
				a.push(((Token) a.pop()).sval());
			}
		};
		Sequence commaList = (Sequence) new Sequence().add(new Word().setAssembler(changeToStringAssembler));
		Parser commaTerm = new Sequence().add(new Symbol(",").discard()).add(new Word().setAssembler(changeToStringAssembler));
		commaList.add(new Repetition(commaTerm));
		Parser content = new Alternation().add(new Empty()).add(commaList);
		parser = new Sequence().add(new Symbol("[").discard()).add(content).add(new Symbol("]").discard());

		result = bestMatch("[]");
		assertTrue(result.popAllMatches().isEmpty());

		result = bestMatch("[a]");
		List<Object> allMatches = result.popAllMatches();
		assertEquals(1, allMatches.size());
		assertEquals("a", allMatches.get(0));

		result = bestMatch("[a, b, c]");
		allMatches = result.popAllMatches();
		assertEquals(3, allMatches.size());
		assertEquals("a", allMatches.get(0));
		assertEquals("b", allMatches.get(1));
		assertEquals("c", allMatches.get(2));
	}

	@Override
	protected Parser getParser() {
		return parser;
	}

}
