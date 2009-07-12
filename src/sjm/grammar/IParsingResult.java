package sjm.grammar;

import java.util.Stack;

import sjm.parse.PubliclyCloneable;
import sjm.parse.tokens.Token;

public interface IParsingResult {

	boolean isCompleteMatch();

	Stack<Token> getStack();

	PubliclyCloneable<? extends Object> getTarget();

}
