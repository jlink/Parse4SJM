package sjm.grammar;

import java.util.Stack;

import sjm.parse.*;
import sjm.parse.tokens.Token;

public class ParsingResult implements IParsingResult {

	private Assembly result;

	public ParsingResult(Assembly result) {
		this.result = result;
	}

	public boolean isCompleteMatch() {
		if (result == null)
			return false;
		return result.elementsRemaining() == 0;
	}

	public Stack<Token> getStack() {
		if (result == null)
			return new Stack<Token>();

		return (Stack<Token>) result.getStack();
	}

	public PubliclyCloneable<? extends Object> getTarget() {
		if (result == null)
			return null;
		return result.getTarget();
	}

	@Override
	public String toString() {
		if (result == null)
			return "null parsing result";
		return result.toString();
	}
}
