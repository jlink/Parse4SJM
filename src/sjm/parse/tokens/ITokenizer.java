package sjm.parse.tokens;

import java.io.IOException;

public interface ITokenizer {

	/**
	 * @return the next token.
	 * 
	 * @exception IOException
	 *                if there is any problem reading
	 */
	Token nextToken() throws IOException;

}