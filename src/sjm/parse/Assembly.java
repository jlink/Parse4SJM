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
 * An assembly maintains a stream of language elements along with stack and
 * target objects.
 * 
 * Parsers use assemblers to record progress at recognizing language elements
 * from assembly's string.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 * 
 */

public abstract class Assembly implements Iterator<Object>, PubliclyCloneable<Assembly> {

	/**
	 * a place to keep track of consumption progress
	 */
	protected Stack<Object> stack = new Stack<Object>();

	/**
	 * Another place to record progress; this is just an object. If a parser
	 * were recognizing an HTML page, for example, it might create a Page object
	 * early, and store it as an assembly's "target". As its recognition of the
	 * HTML progresses, it could use the stack to build intermediate results,
	 * like a heading, and then apply them to the target object.
	 */
	protected PubliclyCloneable<? extends Object> target;

	/**
	 * which element is next
	 */
	protected int index = 0;

	/**
	 * stack size when matching starts
	 */
	protected Stack<Integer> stackSizesBeforeMatch = new Stack<Integer>();
	{
		stackSizesBeforeMatch.push(0);
	}

	/**
	 * Return a copy of this object.
	 * 
	 * @return a copy of this object
	 */
	@Override
	public Assembly clone() {
		try {
			Assembly a = (Assembly) super.clone();
			a.stack = (Stack<Object>) stack.clone();
			a.stackSizesBeforeMatch = (Stack<Integer>) a.stackSizesBeforeMatch.clone();
			if (target != null) {
				a.target = (PubliclyCloneable<? extends Object>) target.clone();
			}
			return a;
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void announceMatchingStart() {
		//System.out.println("announce start: " + this + " " + getStackSizesBeforeMatch());
		this.stackSizesBeforeMatch.push(stack.size());
	}

	public void announceMatchingEnd() {
		//System.out.println("announce end: " + this + " " + getStackSizesBeforeMatch());
		this.stackSizesBeforeMatch.pop();
	}

	/**
	 * For testing purposes only
	 */
	public Stack<Integer> getStackSizesBeforeMatch() {
		return stackSizesBeforeMatch;
	}

	/**
	 * Returns the elements of the assembly that have been consumed, separated
	 * by the specified delimiter.
	 * 
	 * @param String
	 *            the mark to show between consumed elements
	 * 
	 * @return the elements of the assembly that have been consumed
	 */
	public abstract String consumed(String delimiter);

	/**
	 * Returns the default string to show between elements.
	 * 
	 * @return the default string to show between elements
	 */
	protected abstract String defaultDelimiter();

	/**
	 * Returns the number of elements that have been consumed.
	 * 
	 * @return the number of elements that have been consumed
	 */
	public int elementsConsumed() {
		return index;
	}

	/**
	 * Returns the number of elements that have not been consumed.
	 * 
	 * @return the number of elements that have not been consumed
	 */
	public int elementsRemaining() {
		return length() - elementsConsumed();
	}

	/**
	 * Returns this assembly's stack.
	 * 
	 * @return this assembly's stack
	 */
	public Stack<Object> getStack() {
		return stack;
	}

	/**
	 * Returns the object identified as this assembly's "target". Clients can
	 * set and retrieve a target, which can be a convenient supplement as a
	 * place to work, in addition to the assembly's stack. For example, a parser
	 * for an HTML file might use a web page object as its "target". As the
	 * parser recognizes markup commands like <head>, it could apply its
	 * findings to the target.
	 * 
	 * @return the target of this assembly
	 */
	public PubliclyCloneable<? extends Object> getTarget() {
		return target;
	}

	/**
	 * Returns true if this assembly has unconsumed elements.
	 * 
	 * @return true, if this assembly has unconsumed elements
	 */
	public boolean hasNext() {
		return elementsConsumed() < length();
	}

	/**
	 * Returns the number of elements in this assembly.
	 * 
	 * @return the number of elements in this assembly
	 */
	protected abstract int length();

	/**
	 * Shows the next object in the assembly, without removing it
	 * 
	 * @return the next object
	 * 
	 */
	public abstract Object peek();

	/**
	 * Removes the object at the top of this assembly's stack and returns it.
	 * 
	 * @return the object at the top of this assembly's stack
	 * 
	 * @exception EmptyStackException
	 *                if this stack is empty
	 */
	public Object pop() {
		return stack.pop();
	}

	public List<Object> popAllMatches() {
		List<Object> allMatches = new ArrayList<Object>();
		Integer sizeBeforeMatch = stackSizesBeforeMatch.peek();
		while (stack.size() > sizeBeforeMatch) {
			allMatches.add(0, pop());
		}
		return allMatches;
	}

	/**
	 * Pushes an object onto the top of this assembly's stack.
	 * 
	 * @param object
	 *            the object to be pushed
	 */
	public void push(Object o) {
		stack.push(o);
	}

	/**
	 * Returns the elements of the assembly that remain to be consumed,
	 * separated by the specified delimiter.
	 * 
	 * @param String
	 *            the mark to show between unconsumed elements
	 * 
	 * @return the elements of the assembly that remain to be consumed
	 */
	public abstract String remainder(String delimiter);

	/**
	 * Sets the target for this assembly. Targets must implement
	 * <code>clone()</code> as a public method.
	 * 
	 * @param target
	 *            a publicly cloneable object
	 */
	public void setTarget(PubliclyCloneable<? extends Object> target) {
		this.target = target;
	}

	/**
	 * Returns a textual description of this assembly.
	 * 
	 * @return a textual description of this assembly
	 */
	@Override
	public String toString() {
		String delimiter = defaultDelimiter();
		return stack + "(" + (stack.size() - stackSizesBeforeMatch.peek()) + ") : " + consumed(delimiter) + "^" + remainder(delimiter);
	}

	/**
	 * Put back n objects
	 * 
	 */
	public void unget(int n) {
		index -= n;
		if (index < 0) {
			index = 0;
		}
	}
}
