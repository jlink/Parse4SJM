package sjm.grammar;

import java.util.*;

public interface IParserMatched {
	public void apply(List<Object> tokens, Stack<Object> stack);
}
