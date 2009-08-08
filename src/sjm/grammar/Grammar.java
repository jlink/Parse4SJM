package sjm.grammar;

import groovy.lang.Closure;

import java.io.*;
import java.util.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

public class Grammar {

	public static interface InitTokenizer {
		void init(Tokenizer t);
	}

	private final String name;
	private String startRule;
	private Map<String, IParser> rules = new HashMap<String, IParser>();
	private Grammar ruleGrammar;
	private InitTokenizer initTokenizer;
	private Map<String, Class<? extends Terminal>> terminalTypes = new HashMap<String, Class<? extends Terminal>>();
	private boolean discardAllConstantsInTextualRules = false;
	private boolean hasBeenChecked = false;

	public Grammar(String name) {
		this.name = name;
		registerDefaultTerminalTypes();
	}

	private void registerDefaultTerminalTypes() {
		registerTerminal(Num.class);
		registerTerminal(Int.class);
		registerTerminal(Word.class);
		registerTerminal(QuotedString.class);
		registerTerminal(UpperCaseWord.class);
		registerTerminal(LowerCaseWord.class);
	}

	public String getName() {
		return name;
	}

	public IParsingResult parse(String stringToParse) {
		check();
		Tokenizer tokenizer = new Tokenizer(stringToParse);
		Assembly assembly = new TokenAssembly(tokenizer);
		if (initTokenizer != null)
			initTokenizer.init(tokenizer);
		Assembly result = startParser().bestMatch(assembly);
		return new ParsingResult(result);
	}

	public void addRule(String ruleName, IParser parser) {
		rules.put(ruleName, parser);
		if (startRule == null)
			markAsStartRule(ruleName);
		hasBeenChecked = false;
	}

	public void addRule(String ruleName, IParser parser, IAssembler assembler) {
		addRule(ruleName, parser);
		addAssembler(ruleName, assembler);
	}

	public void markAsStartRule(String ruleName) {
		this.startRule = ruleName;
		hasBeenChecked = false;
	}

	public void check() {
		if (hasBeenChecked)
			return;
		checkGrammarHasStartRule();
		checkGrammarIsClosed();
		checkNoLeftRecursion();
		hasBeenChecked = true;
	}

	private void checkNoLeftRecursion() {
		new LeftRecursionChecker(this).check();
	}

	private void checkGrammarIsClosed() {
		Set<IParser> seen = allParsersUsedFromStartRule();
		checkAllClausesAreUsed(seen);
	}

	private void checkAllClausesAreUsed(Set<IParser> seen) {
		for (Map.Entry<String, IParser> entry : rules.entrySet()) {
			if (!seen.contains(entry.getValue()))
				throw new GrammarException("Clause '" + entry.getKey() + "' is not being referenced from start rule.");
		}
	}

	private Set<IParser> allParsersUsedFromStartRule() {
		Set<IParser> all = ParserCollector.collectAllReferencedParsers(startParser());
		all.add(startParser());
		return all;
	}

	private void checkGrammarHasStartRule() {
		if (startParser() == null)
			throw new GrammarException("A start rule must be specified");
	}

	public IParser startParser() {
		return rules.get(startRule);
	}

	public IParser getRule(String ruleName) {
		return rules.get(ruleName);
	}

	public String defineRule(String ruleText) {
		return (String) getRuleGrammar().parse(ruleText).getStack().pop();
	}

	public String defineRule(String ruleText, IParserMatched matched) {
		String ruleName = defineRule(ruleText);
		addAssembler(ruleName, new ParserMatchedAssembler(matched));
		return ruleName;
	}

	public String defineRule(String ruleText, final Closure closure) {
		IParserMatched matched = new IParserMatched() {
			public void apply(List<Object> tokens, Stack<Object> stack) {
				closure.call(new Object[] { tokens, stack });
			}
		};
		return defineRule(ruleText, matched);
	}

	/**
	 * Create lazily since not all Grammars need it
	 */
	private Grammar getRuleGrammar() {
		if (ruleGrammar == null)
			ruleGrammar = new RuleGrammar(this);
		return ruleGrammar;
	}

	public void addAssembler(String ruleName, IAssembler assembler) {
		if (!ruleExists(ruleName)) {
			throw new GrammarException("Rule '" + ruleName + "' does not exist.");
		}
		getRule(ruleName).setAssembler(assembler);
	}

	private boolean ruleExists(String ruleName) {
		return rules.containsKey(ruleName);
	}

	public void initTokenizer(InitTokenizer initializer) {
		this.initTokenizer = initializer;
	}

	public void printOn(PrintWriter writer) {
		printRuleOn(startRule, writer);
		for (String ruleName : rules.keySet()) {
			if (ruleName.equals(startRule))
				continue;
			printRuleOn(ruleName, writer);
		}

	}

	private void printRuleOn(String ruleName, PrintWriter writer) {
		String ruleString = getRule(ruleName).toString();
		if (ruleString.startsWith("(") && ruleString.endsWith(")"))
			ruleString = ruleString.substring(1, ruleString.length() - 1);
		writer.println(ruleName + " = " + ruleString + ";");
	}

	public void registerTerminal(Class<? extends Terminal> terminalClass) {
		String terminalType = terminalClass.getSimpleName();
		registerTerminal(terminalType, terminalClass);
	}

	public void registerTerminal(String terminalType, Class<? extends Terminal> terminalClass) {
		terminalTypes.put(terminalType.toLowerCase(), terminalClass);
	}

	public Terminal terminal(String terminalType) {
		Class<? extends Terminal> terminalClass = terminalTypes.get(terminalType.toLowerCase());
		if (terminalClass == null)
			throw new GrammarException("Unknown terminal type: " + terminalType);
		try {
			return terminalClass.newInstance();
		} catch (Exception e) {
			throw new GrammarException("Cannot create instance of terminal type: " + terminalType + ". Reason: " + e.getMessage());
		}
	}

	/**
	 * Only valid for textual rules added as texts
	 */
	public void discardAllConstants() {
		discardAllConstantsInTextualRules = true;
	}

	public boolean areAllConstantsDiscarded() {
		return discardAllConstantsInTextualRules;
	}

	/**
	 * Read a rule from each line in reader until there are no more lines, but
	 * don't close the reader
	 * 
	 * @param reader
	 * @throws IOException
	 */
	public void addRulesFrom(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		while (line != null) {
			defineRule(line);
			line = br.readLine();
		}
	}

}
