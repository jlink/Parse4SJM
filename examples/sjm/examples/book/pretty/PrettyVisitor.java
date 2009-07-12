package sjm.examples.book.pretty;

import java.util.*;

import sjm.parse.*;

/**
 * An object of this class visits the parsers in a parser composite and sets
 * each subparser's assembler to be one of the "pretty" assemblers in this
 * package. These assemblers build a tree of nodes from the
 * <code>ComponentNode</code> hierarchy that is also in this package. The
 * resulting tree effectively records the order in which the parse proceeds.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class PrettyVisitor extends ParserVisitor {
	/**
	 * Set an <code>Alternation</code> parser's assembler to be a
	 * <code>PrettyAlternationAssembler</code> object and visit this parser's
	 * children.
	 */
	public void visitAlternation(Alternation a, Set<Parser> visited) {
		if (visited.contains(a)) {
			return;
		}
		visited.add(a);
		a.setAssembler(new PrettyAlternationAssembler(a.getName()));
		for (Parser child : a.getSubparsers()) {
			child.accept(this, visited);
		}
	}

	/**
	 * Set an <code>Empty</code> parser's assembler to be a
	 * <code>PrettyEmptyAssembler</code> object.
	 */
	public void visitEmpty(Empty e, Set<Parser> visited) {
		e.setAssembler(new PrettyEmptyAssembler());
	}

	/**
	 * Set a <code>Repetition</code> parser's pre-assembler to push a "fence",
	 * and set the parser's post-assembler to be a
	 * <code>PrettyRepetitionAssembler</code> object. The latter assembler will
	 * pop results down to the fence. Also visit the repetition parser's
	 * subparser.
	 */
	public void visitRepetition(Repetition r, Set<Parser> visited) {
		if (visited.contains(r)) {
			return;
		}
		visited.add(r);
		Object fence = new Object();
		r.setPreAssembler(new FenceAssembler(fence));
		r.setAssembler(new PrettyRepetitionAssembler(r.getName(), fence));
		r.getSubparser().accept(this, new HashSet<Parser>(visited));
	}

	/**
	 * Set a <code>Sequence</code> parser's assembler to be a
	 * <code>PrettySequenceAssembler</code> object and visit the parser's
	 * children.
	 */
	public void visitSequence(Sequence s, Set<Parser> visited) {
		if (visited.contains(s)) {
			return;
		}
		visited.add(s);
		s.setAssembler(new PrettySequenceAssembler(s.getName(), s.getSubparsers().size()));
		for (Parser child : s.getSubparsers()) {
			child.accept(this, visited);
		}
	}

	/**
	 * Set a <code>Terminal</code> object's assembler to be a
	 * <code>PrettyTerminalAssembler</code> object.
	 */
	public void visitTerminal(Terminal t, Set<Parser> visited) {
		t.setAssembler(new PrettyTerminalAssembler());
	}
}
