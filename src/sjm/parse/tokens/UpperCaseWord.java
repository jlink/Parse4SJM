package sjm.parse.tokens;

import java.util.Set;

import sjm.parse.Parser;

public class UpperCaseWord extends Word {
	@Override
	protected boolean qualifies(Object o) {
		if (!super.qualifies(o))
			return false;
		Token t = (Token) o;
		return Character.isUpperCase(t.sval().charAt(0));
	}

	@Override
	protected char workOnCharForRandomExpansion(char c, int pos) {
		if (pos == 0)
			return Character.toLowerCase(c);
		return c;
	}

	@Override
	public String unvisitedString(Set<Parser> visited) {
		return "UpperCaseWord";
	}
}
