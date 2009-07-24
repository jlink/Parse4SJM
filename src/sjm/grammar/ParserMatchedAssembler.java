package sjm.grammar;

import java.util.List;

import sjm.parse.*;

public class ParserMatchedAssembler implements IAssembler {

	private final IParserMatched matchedRule;

	public ParserMatchedAssembler(IParserMatched matchedRule) {
		this.matchedRule = matchedRule;
	}

	public void workOn(Assembly a) {
		List<Object> matches = a.popAllMatches();
		matchedRule.apply(matches, a.getStack());
	}

}
