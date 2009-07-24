package sjm.grammar;

import java.util.Stack;

import sjm.parse.PubliclyCloneable;

public interface IParsingResult {

	boolean isCompleteMatch();

	Stack<Object> getStack();

	PubliclyCloneable<? extends Object> getTarget();

}
