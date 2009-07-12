package sjm.examples.book.pretty;

import java.util.Enumeration;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show that the pretty printer will find all the parses that
 * result from applying the parser <code>Word* Word*</code>
 * against a string with four words.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowPrettyRepetitions {
	/**
	 * Show that the pretty printer will find all the parses that
	 * result from applying the parser <code>Word* Word*</code>
	 * against a string with four words.
	 */
	public static void main(String[] args) {
		PrettyParser p = new PrettyParser(seq());
		p.setShowLabels(true);
		TokenAssembly ta = new TokenAssembly("belfast cork dublin limerick");
		Enumeration e = p.parseTrees(ta).elements();
		while (e.hasMoreElements()) {
			System.out.println("The input parses as:");
			System.out.println("---------------------------");
			System.out.println(e.nextElement());
		}
	}

	/**
	 * The parser to try:
	 *
	 * <blockquote><pre> 
	 *     seq  = rep1 rep2;
	 *     rep1 = Word*;
	 *     rep2 = Word*;
	 * </pre></blockquote>
	 * 
	 */
	public static Sequence seq() {
		Sequence seq = new Sequence("<seq>");
		seq.add(new Repetition(new Word(), "<rep1>"));
		seq.add(new Repetition(new Word(), "<rep2>"));
		return seq;
	}
}
