package sjm.grammar;

import java.util.Set;

import sjm.parse.*;
import sjm.parse.tokens.*;

public class RuleGrammar extends Grammar {

	private final Grammar targetGrammar;

	public RuleGrammar(Grammar targetGrammar) {
		super("rule grammar");
		this.targetGrammar = targetGrammar;
		intTerminalTypes();
		initRules();
	}

	private void intTerminalTypes() {
		registerTerminal(SymbolDef.class);
		registerTerminal(CaselessLiteralDef.class);
	}

	private void initRules() {

		addRule("rule", seq(new LowerCaseWord(), sym("=").discard(), ref("expression"), alt(sym(";").discard(), new Empty())));
		addAssembler("rule", new RuleAssembler());

		addRule("expression", seq(ref("sequence"), rep(ref("alternationSequence"))));

		addRule("sequence", alt(ref("factor"), ref("sequenceFactor")));

		addRule("sequenceFactor", seq(ref("factor"), ref("sequence")));
		addAssembler("sequenceFactor", new SequenceAssembler());

		addRule("alternationSequence", seq(sym("|").discard(), (ref("sequence"))));
		addAssembler("alternationSequence", new AlternationAssembler());

		addRule("factor", alt(ref("phrase"), ref("repetition"), ref("atLeastOne")));

		addRule("repetition", seq(ref("phrase"), sym("*").discard()));
		addAssembler("repetition", new RepetitionAssembler(0));

		addRule("atLeastOne", seq(ref("phrase"), sym("+").discard()));
		addAssembler("atLeastOne", new RepetitionAssembler(1));

		addRule("phrase", alt(seq(sym("(").discard(), ref("expression"), sym(")").discard()), ref("atom")));

		addRule("atom", alt(ref("caseless"), ref("symbol"), ref("discard"), ref("reference"), ref("terminal")));

		addRule("discard", seq(sym("#").discard(), alt(ref("symbol"), ref("caseless"))));
		addAssembler("discard", new DiscardAssembler());

		addRule("caseless", terminal("CaselessLiteralDef"));
		addAssembler("caseless", new CaselessAssembler());

		addRule("symbol", terminal("SymbolDef"));
		addAssembler("symbol", new SymbolAssembler());

		addRule("reference", terminal("LowerCaseWord"));
		addAssembler("reference", new ReferenceAssembler());

		addRule("terminal", terminal("UpperCaseWord"));
		addAssembler("terminal", new TerminalAssembler());
	}

	@Override
	public String defineRule(String ruleText) {
		throw new GrammarException("addTextualRule() does not work in RuleGrammar to prevent infinite recursion.");
	}

	private Repetition rep(Parser parser) {
		return new Repetition(parser);
	}

	private RuleReference ref(String ruleName) {
		return new RuleReference(ruleName, this);
	}

	private Sequence seq(Parser... parsers) {
		Sequence seq = new Sequence();
		for (Parser each : parsers) {
			seq.add(each);
		}
		return seq;
	}

	private Alternation alt(Parser... parsers) {
		Alternation alt = new Alternation();
		for (Parser each : parsers) {
			alt.add(each);
		}
		return alt;
	}

	private Symbol sym(String symbol) {
		return new Symbol(symbol);
	}

	class SequenceAssembler implements IAssembler {
		public void workOn(Assembly a) {
			Parser last = (Parser) a.pop();
			Parser butlast = (Parser) a.pop();
			Sequence seq;
			if (last instanceof Sequence) {
				seq = (Sequence) last;
			} else {
				seq = seq(last);
			}
			seq.addTop(butlast);
			a.push(seq);
		}
	}

	class AlternationAssembler implements IAssembler {
		public void workOn(Assembly a) {
			Parser last = (Parser) a.pop();
			Parser butlast = (Parser) a.pop();
			Alternation alt;
			if (butlast instanceof Alternation) {
				alt = (Alternation) butlast;
			} else {
				alt = alt(butlast);
			}
			alt.add(last);
			a.push(alt);
		}
	}

	class RuleAssembler implements IAssembler {
		public void workOn(Assembly a) {
			if (a.elementsRemaining() > 0)
				return;
			Parser parser = (Parser) a.pop();
			String ruleName = ((Token) a.pop()).sval();
			targetGrammar.addRule(ruleName, parser);
			a.push(ruleName);
		}
	}

	class CaselessAssembler implements IAssembler {
		public void workOn(Assembly a) {
			String quoted = ((Token) a.pop()).sval();
			String caseless = quoted.substring(1, quoted.length() - 1);
			CaselessLiteral constant = new CaselessLiteral(caseless);
			if (targetGrammar.areAllConstantsDiscarded())
				constant.discard();
			a.push(constant);
		}
	}

	class DiscardAssembler implements IAssembler {
		public void workOn(Assembly a) {
			Terminal t = (Terminal) a.getStack().peek();
			t.discard();
		}
	}

	class SymbolAssembler implements IAssembler {
		public void workOn(Assembly a) {
			String quoted = ((Token) a.pop()).sval();
			String symbol = quoted.substring(1, quoted.length() - 1);
			Symbol constant = new Symbol(symbol);
			if (targetGrammar.areAllConstantsDiscarded())
				constant.discard();
			a.push(constant);
		}
	}

	class TerminalAssembler implements IAssembler {
		public void workOn(Assembly a) {
			String terminalType = ((Token) a.pop()).sval();
			a.push(targetGrammar.terminal(terminalType));
		}
	}

	class ReferenceAssembler implements IAssembler {
		public void workOn(Assembly a) {
			String ruleName = ((Token) a.pop()).sval();
			a.push(new RuleReference(ruleName, targetGrammar));
		}
	}

	class RepetitionAssembler implements IAssembler {
		private final int requiredMatches;

		public RepetitionAssembler(int requiredMatches) {
			this.requiredMatches = requiredMatches;
		}

		public void workOn(Assembly a) {
			Parser atomicStep = (Parser) a.pop();
			a.push(new Repetition(atomicStep).requireMatches(requiredMatches));
		}
	}
}

class CaselessLiteralDef extends QuotedString {
	@Override
	protected boolean qualifies(Object o) {
		if (!super.qualifies(o))
			return false;
		Token t = (Token) o;
		String quotedString = t.sval();
		if (!quotedString.matches("\"\\w+\""))
			return false;
		return true;
	}

	@Override
	public String unvisitedString(Set<Parser> visited) {
		return "CaselessLiteralDef";
	}

	@Override
	public boolean isConstant() {
		return true;
	}

}

class SymbolDef extends QuotedString {
	@Override
	protected boolean qualifies(Object o) {
		if (!super.qualifies(o))
			return false;
		Token t = (Token) o;
		String quotedString = t.sval();
		if (!quotedString.matches("'.'"))
			return false;
		return true;
	}

	@Override
	public String unvisitedString(Set<Parser> visited) {
		return "SymbolDef";
	}

	@Override
	public boolean isConstant() {
		return true;
	}

}
