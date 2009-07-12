package sjm.examples.book.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import sjm.examples.book.engine.*;

public class LogikusTest {
	
	@Test
	public void atom() {
		Axiom axiom = LogikusFacade.axiom("johannes");
		assertTrue(axiom.head() instanceof Atom);
		assertEquals("johannes", axiom.head().eval());
	}

	@Test
	public void fact() {
		Axiom axiom = LogikusFacade.axiom("male(johannes)");
		Structure structure = axiom.head();
		assertEquals("male(johannes)", structure.toString());
	}

	@Test
	public void list() {
		Axiom axiom = LogikusFacade.axiom("members([johannes, frank])");
		Structure structure = axiom.head();
		Term list = structure.terms()[0];
		assertEquals("[johannes, frank]", list.toString());
	}

	@Test
	public void listWithTail() {
		Axiom axiom = LogikusFacade.axiom("members([johannes | [frank]])");
		Structure structure = axiom.head();
		Term list = structure.terms()[0];
		assertEquals("[johannes, frank]", list.toString());
	}
}
