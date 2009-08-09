package sjm.grammar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

import sjm.parse.Assembly;
import sjm.parse.tokens.*;

public class ParserMatchedAssemblerTest {

	private IParserMatched matchedRule;
	private ParserMatchedAssembler assembler;
	private Assembly assembly;

	@Before
	public void Init() {
		matchedRule = mock(IParserMatched.class);
		assembler = new ParserMatchedAssembler(matchedRule);
		assembly = new TokenAssembly("");
	}

	@Test
	public void emptyMatch() {
		assembler.workOn(assembly);
		verify(matchedRule).apply(Collections.emptyList(), new Stack<Object>());
	}

	@Test
	public void oneElementMatch() {
		assembly.push(new Token("hello"));
		assembler.workOn(assembly);
		List<Object> expectedMatches = new ArrayList<Object>();
		expectedMatches.add(new Token("hello"));
		verify(matchedRule).apply(expectedMatches, new Stack<Object>());
	}

	@Test
	public void severalElementsMatch() {
		assembly.push("a result");
		assembly.announceMatchingStart();
		assembly.push(new Token("b"));
		assembly.push(new Token("c"));

		assembler.workOn(assembly);

		List<Object> expectedMatches = new ArrayList<Object>();
		expectedMatches.add(new Token("b"));
		expectedMatches.add(new Token("c"));
		Stack<Object> expectedStack = new Stack<Object>();
		expectedStack.add("a result");
		verify(matchedRule).apply(expectedMatches, expectedStack);
	}

	@Test
	public void matchedRuleCanManipulateStack() {
		assembly.push("a");
		assembly.announceMatchingStart();
		assembly.push(new Token("b"));

		matchedRule = new IParserMatched() {
			public void apply(List<Object> tokens, Stack<Object> stack) {
				String result = stack.pop() + ((Token) tokens.get(0)).sval();
				stack.push(result);
			}
		};

		assembler = new ParserMatchedAssembler(matchedRule);
		assembler.workOn(assembly);

		assertThat((String) assembly.getStack().peek(), is("ab"));
	}

}
