package sjm.parse.tokens;

import java.io.*;
import java.math.BigDecimal;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A NumberState object returns a number from a reader. This state's idea of a
 * number allows an optional, initial minus sign, followed by one or more
 * digits. A decimal point and another string of digits may follow these digits.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class NumberState extends TokenizerState {
	protected int c;
	protected BigDecimal value;
	protected boolean absorbedLeadingMinus;
	protected boolean absorbedDot;
	protected boolean gotAdigit;

	/*
	 * Convert a stream of digits into a number, making this number a fraction
	 * if the boolean parameter is true.
	 */
	protected BigDecimal absorbDigits(PushbackReader r, boolean fraction) throws IOException {

		int divideByPowerOfTen = 0;
		BigDecimal v = BigDecimal.ZERO;
		while ('0' <= c && c <= '9') {
			gotAdigit = true;
			v = v.multiply(new BigDecimal(10)).add(new BigDecimal(c - '0'));
			c = r.read();
			if (fraction) {
				divideByPowerOfTen++;
			}
		}
		if (fraction) {
			v = v.movePointLeft(divideByPowerOfTen);
		}
		return v;
	}

	/**
	 * Return a number token from a reader.
	 * 
	 * @return a number token from a reader
	 */
	@Override
	public Token nextToken(PushbackReader r, int cin, Tokenizer t) throws IOException {

		reset(cin);
		parseLeft(r);
		parseRight(r);
		r.unread(c);
		return value(r, t);
	}

	/*
	 * Parse up to a decimal point.
	 */
	protected void parseLeft(PushbackReader r) throws IOException {

		if (c == '-') {
			c = r.read();
			absorbedLeadingMinus = true;
		}
		value = absorbDigits(r, false);
	}

	/*
	 * Parse from a decimal point to the end of the number.
	 */
	protected void parseRight(PushbackReader r) throws IOException {

		if (c == '.') {
			c = r.read();
			absorbedDot = true;
			value = value.add(absorbDigits(r, true));
		}
	}

	/*
	 * Prepare to assemble a new number.
	 */
	protected void reset(int cin) {
		c = cin;
		value = BigDecimal.ZERO;
		absorbedLeadingMinus = false;
		absorbedDot = false;
		gotAdigit = false;
	}

	/*
	 * Put together the pieces of a number.
	 */
	protected Token value(PushbackReader r, Tokenizer t) throws IOException {

		if (!gotAdigit) {
			if (absorbedLeadingMinus && absorbedDot) {
				r.unread('.');
				return t.symbolState().nextToken(r, '-', t);
			}
			if (absorbedLeadingMinus) {
				return t.symbolState().nextToken(r, '-', t);
			}
			if (absorbedDot) {
				return t.symbolState().nextToken(r, '.', t);
			}
		}
		if (absorbedLeadingMinus) {
			value = value.negate();
		}
		return new Token(Token.TT_NUMBER, "", value);
	}
}
