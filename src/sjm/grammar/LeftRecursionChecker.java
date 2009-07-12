package sjm.grammar;

import java.util.Set;

import sjm.parse.IParser;

public class LeftRecursionChecker {

	private final Grammar grammar;

	public LeftRecursionChecker(Grammar grammar) {
		this.grammar = grammar;
	}

	public void check() {
		Set<IParser> allParsers = ParserCollector.collectAllReferencedParsers(grammar.startParser());
		for (IParser each : allParsers) {
			checkLeftRecursionFor(each);
		}
	}

	private void checkLeftRecursionFor(IParser candidate) {
		Set<IParser> allLeftChildren = ParserCollector.collectLeftChildren(candidate);
		if (allLeftChildren.contains(candidate))
			throw new GrammarException("Left recursion detected for: " + candidate);
	}

}
