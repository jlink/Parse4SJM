package sjm.examples.book.coffee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sjm.parse.Assembly;
import sjm.parse.tokens.*;

public class CoffeeParserTest {

	@Test
	public void examples() {
		Tokenizer t = CoffeeParser.tokenizer();
		t.setString("Thai Bulenc (Manchester), black, Argentina, 3.0");
		Assembly result = CoffeeParser.start().bestMatch(new TokenAssembly(t));
		Coffee coffee = (Coffee) result.getTarget();
		assertEquals("Thai Bulenc", coffee.getName());
		assertEquals("Manchester", coffee.getFormerName());
		assertEquals("black", coffee.getRoast());
		assertEquals("Argentina", coffee.getCountry());
		assertEquals(3.0, coffee.getPrice(), 0.0);
	}
}
