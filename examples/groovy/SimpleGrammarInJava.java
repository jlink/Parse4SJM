package groovy;

import java.math.BigDecimal;
import java.util.*;

import sjm.grammar.*;
import sjm.parse.tokens.Token;

public class SimpleGrammarInJava {

	public static void main(String[] args) {

		Grammar mathGrammar = new Grammar("a simple math grammar");

		mathGrammar.defineRule("term = Num;", new IParserMatched() {
			public void apply(List<Object> matches, Stack<Object> stack) {
				stack.push(((Token) matches.get(0)).value());
			}
		});

		IParsingResult result = mathGrammar.parse("5.1");
		assert result.getStack().pop() == new BigDecimal("5.1");

		System.out.println((BigDecimal) result.getStack().pop());
	}
}
