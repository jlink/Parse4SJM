package sjm.parse;

import java.util.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A <code>Parser</code> is an object that recognizes the elements of a
 * language.
 * <p>
 * Each <code>Parser</code> object is either a <code>
 * Terminal</code> or a composition
 * of other parsers. The <code>Terminal</code> class is a subclass of <code>
 * Parser</code>
 * , and is itself a hierarchy of parsers that recognize specific patterns of
 * text. For example, a <code>Word</code> recognizes any word, and a
 * <code>Literal</code> matches a specific string.
 * <p>
 * In addition to <code>Terminal</code>, other subclasses of <code>Parser</code>
 * provide composite parsers, describing sequences, alternations, and
 * repetitions of other parsers. For example, the following <code>
 * Parser</code>
 * objects culminate in a <code>good
 * </code> parser that recognizes a description of good
 * coffee.
 * 
 * <blockquote>
 * 
 * <pre>
 * Alternation adjective = new Alternation();
 * adjective.add(new Literal(&quot;steaming&quot;));
 * adjective.add(new Literal(&quot;hot&quot;));
 * Sequence good = new Sequence();
 * good.add(new Repetition(adjective));
 * good.add(new Literal(&quot;coffee&quot;));
 * String s = &quot;hot hot steaming hot coffee&quot;;
 * Assembly a = new TokenAssembly(s);
 * System.out.println(good.bestMatch(a));
 * </pre>
 * 
 * </blockquote>
 * 
 * This prints out:
 * 
 * <blockquote>
 * 
 * <pre>
 *     [hot, hot, steaming, hot, coffee]
 *     hot/hot/steaming/hot/coffee&circ;
 * </pre>
 * 
 * </blockquote>
 * 
 * The parser does not match directly against a string, it matches against an
 * <code>Assembly</code>. The resulting assembly shows its stack, with four
 * words on it, along with its sequence of tokens, and the index at the end of
 * these. In practice, parsers will do some work on an assembly, based on the
 * text they recognize.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */

public abstract class Parser implements IParser {
	/*
	 * a name to identify this parser
	 */
	protected String name;
	/*
	 * an object that will work on an assembly whenever this parser successfully
	 * matches against the assembly
	 */
	protected IAssembler assembler;

	/**
	 * Constructs a nameless parser.
	 */
	public Parser() {
	}

	/**
	 * Constructs a parser with the given name.
	 * 
	 * @param String
	 *            A name to be known by. For parsers that are deep composites, a
	 *            simple name identifying its purpose is useful.
	 */
	public Parser(String name) {
		this.name = name;
	}

	/**
	 * Accepts a "visitor" which will perform some operation on a parser
	 * structure. The book, "Design Patterns", explains the visitor pattern.
	 * 
	 * @param ParserVisitor
	 *            the visitor to accept
	 */
	public void accept(ParserVisitor pv) {
		accept(pv, new HashSet<Parser>());
	}

	/**
	 * Accepts a "visitor" along with a collection of previously visited
	 * parsers.
	 * 
	 * @param ParserVisitor
	 *            the visitor to accept
	 * 
	 * @param Vector
	 *            a collection of previously visited parsers.
	 */
	public abstract void accept(ParserVisitor pv, Set<Parser> visited);

	/**
	 * Returns the most-matched assembly in a collection.
	 * 
	 * @return the most-matched assembly in a collection.
	 * 
	 * @param v the collection to look through
	 * 
	 */
	public Assembly best(Set<Assembly> v) {
		Assembly best = null;
		for (Assembly a : v) {
			if (!a.hasNext()) {
				return a;
			}
			if (best == null) {
				best = a;
			} else if (a.elementsConsumed() > best.elementsConsumed()) {
				best = a;
			}
		}
		return best;
	}

	/**
	 * Returns an assembly with the greatest possible number of elements
	 * consumed by matches of this parser.
	 * 
	 * @return an assembly with the greatest possible number of elements
	 *         consumed by this parser
	 * 
	 * @param Assembly
	 *            an assembly to match against
	 * 
	 */
	public Assembly bestMatch(Assembly a) {
		Set<Assembly> in = new HashSet<Assembly>();
		in.add(a);
		Set<Assembly> out = matchAndAssemble(in);
		return best(out);
	}

	/**
	 * Returns either null, or a completely matched version of the supplied
	 * assembly.
	 * 
	 * @return either null, or a completely matched version of the supplied
	 *         assembly
	 * 
	 * @param Assembly
	 *            an assembly to match against
	 * 
	 */
	public Assembly completeMatch(Assembly a) {
		Assembly best = bestMatch(a);
		if (best != null && !best.hasNext()) {

			return best;
		}
		return null;
	}

	/**
	 * Create a copy of a Set, cloning each element of the vector.
	 * 
	 * @param in the Set to copy
	 * 
	 * @return a copy of the input Set, cloning each element of it
	 */
	public static Set<Assembly> elementClone(Set<Assembly> v) {
		Set<Assembly> copy = new HashSet<Assembly>();
		for (Assembly a : v) {
			copy.add(a.clone());
		}
		return copy;
	}

	/**
	 * Returns the name of this parser.
	 * 
	 * @return the name of this parser
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Given a set (well, a <code>Vector</code>, really) of assemblies, this
	 * method matches this parser against all of them, and returns a new set
	 * (also really a <code>Vector</code>) of the assemblies that result from
	 * the matches.
	 * <p>
	 * For example, consider matching the regular expression <code>a*</code>
	 * against the string <code>"aaab"</code>. The initial set of states is
	 * <code>{^aaab}</code>, where the ^ indicates how far along the assembly
	 * is. When <code>a*</code> matches against this initial state, it creates a
	 * new set <code>{^aaab, a^aab, aa^ab, 
	 * aaa^b}</code>.
	 * 
	 * @return a Vector of assemblies that result from matching against a
	 *         beginning set of assemblies
	 * 
	 * @param Vector
	 *            a vector of assemblies to match against
	 * 
	 */
	public abstract Set<Assembly> match(Set<Assembly> in);

	/**
	 * Match this parser against an input state, and then apply this parser's
	 * assembler against the resulting state.
	 * 
	 * @return a Vector of assemblies that result from matching against a
	 *         beginning set of assemblies
	 * 
	 * @param Vector
	 *            a vector of assemblies to match against
	 * 
	 */
	public Set<Assembly> matchAndAssemble(Set<Assembly> in) {
		announceMatchingStart(in);
		Set<Assembly> out = match(in);
		if (assembler != null) {
			for (Assembly assembly : out) {
				assembler.workOn(assembly);
			}
		}
		announceMatchingEnd(out);
		return out;
	}

	private void announceMatchingStart(Set<Assembly> in) {
		for (Assembly each : in) {
			each.announceMatchingStart();
		}
	}

	private void announceMatchingEnd(Set<Assembly> out) {
		for (Assembly each : out) {
			each.announceMatchingEnd();
		}
	}

	/**
	 * Create a random expansion for this parser, where a concatenation of the
	 * returned collection will be a language element.
	 */
	protected abstract List<String> randomExpansion(int maxDepth, int depth);

	/**
	 * Return a random element of this parser's language.
	 * 
	 * @return a random element of this parser's language
	 */
	public String randomInput(int maxDepth, String separator) {
		StringBuffer buf = new StringBuffer();
		Iterator<String> e = randomExpansion(maxDepth, 0).iterator();
		boolean first = true;
		while (e.hasNext()) {
			if (!first) {
				buf.append(separator);
			}
			buf.append(e.next());
			first = false;
		}
		return buf.toString();
	}

	/**
	 * Sets the object that will work on an assembly whenever this parser
	 * successfully matches against the assembly.
	 * 
	 * @param Assembler
	 *            the assembler to apply
	 * 
	 * @return Parser this
	 */
	public Parser setAssembler(IAssembler assembler) {
		this.assembler = assembler;
		return this;
	}

	/**
	 * Returns a textual description of this parser.
	 * 
	 * @return String a textual description of this parser, taking care to avoid
	 *         infinite recursion
	 */
	@Override
	public String toString() {
		return toString(new HashSet<Parser>());
	}

	/**
	 * Returns a textual description of this parser. Parsers can be recursive,
	 * so when building a descriptive string, it is important to avoid infinite
	 * recursion by keeping track of the objects already described. This method
	 * keeps an object from printing twice, and uses
	 * <code>unvisitedString</code> which subclasses must implement.
	 * 
	 * @param Vector
	 *            a list of objects already printed
	 * 
	 * @return a textual version of this parser, avoiding recursion
	 */
	protected String toString(Set<Parser> visited) {
		if (name != null) {
			return name;
		} else if (visited.contains(this)) {
			return "...";
		} else {
			visited.add(this);
			return unvisitedString(visited);
		}
	}

	public boolean isTerminal() {
		return false;
	}

	public boolean isConstant() {
		return false;
	}

	public Set<IParser> leftChildren() {
		return Collections.emptySet();
	}

	/*
	 * Returns a textual description of this string.
	 */
	protected abstract String unvisitedString(Set<Parser> visited);

}
