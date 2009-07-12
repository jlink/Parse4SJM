package sjm.examples.book.imperative;

import java.util.*;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class contains a sequence of other commands.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class CommandSequence extends Command {
	protected List<Command> commands;

	/**
	 * Add a command to the sequence of commands to which this object will
	 * cascade an <code>execute</code> command.
	 * 
	 * @param Command
	 *            a command to add to this command sequence
	 */
	public void addCommand(Command c) {
		commands().add(c);
	}

	/**
	 * Lazy-initialize the <code>commands</code> vector.
	 */
	protected List<Command> commands() {
		if (commands == null) {
			commands = new ArrayList<Command>();
		}
		return commands;
	}

	/**
	 * Ask each command in the sequence to <code>execute</code>.
	 */
	public void execute() {
		for (Command each : commands()) {
			Thread.yield();
			each.execute();
			
		}
	}

	/**
	 * Returns a string description of this command sequence.
	 * 
	 * @return a string description of this command sequence
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean needLine = false;
		for (Command each : commands()) {
			if (needLine) {
				buf.append("\n");
			}
			buf.append(each);
			needLine = true;
		}
		return buf.toString();
	}
}
