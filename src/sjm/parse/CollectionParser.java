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
 * This class abstracts the behavior common to parsers that consist of a series
 * of other parsers.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public abstract class CollectionParser extends Parser {
	/**
	 * the parsers this parser is a collection of
	 */
	protected List<Parser> subparsers = new ArrayList<Parser>();

	/**
	 * Supports subclass constructors with no arguments.
	 */
	public CollectionParser() {
	}

	/**
	 * Supports subclass constructors with a name argument
	 * 
	 * @param string
	 *            the name of this parser
	 */
	public CollectionParser(String name) {
		super(name);
	}

	/**
	 * Adds a parser to the collection.
	 * 
	 * @param Parser
	 *            the parser to add
	 * 
	 * @return this
	 */
	public CollectionParser add(Parser e) {
		subparsers.add(e);
		return this;
	}

	public CollectionParser addTop(Parser e) {
		subparsers.add(0, e);
		return this;
	}

	public Parser getChild(int index) {
		return getSubparsers().get(index);
	}

	/**
	 * Return this parser's subparsers.
	 * 
	 * @return Vector this parser's subparsers
	 */
	public List<Parser> getSubparsers() {
		return subparsers;
	}

	/**
	 * Helps to textually describe this CollectionParser.
	 * 
	 * @returns the string to place between parsers in the collection
	 */
	protected abstract String toStringSeparator();

	/**
	 * Returns a textual description of this parser.
	 */
	@Override
	protected String unvisitedString(Set<Parser> visited) {
		StringBuffer buf = new StringBuffer("(");
		boolean needSeparator = false;
		Iterator<Parser> e = subparsers.iterator();
		while (e.hasNext()) {
			if (needSeparator) {
				buf.append(toStringSeparator());
			}
			Parser next = e.next();
			buf.append(next.toString(visited));
			needSeparator = true;
		}
		buf.append(")");
		return buf.toString();
	}

	public Set<IParser> children() {
		return new HashSet<IParser>(subparsers);
	}

}
