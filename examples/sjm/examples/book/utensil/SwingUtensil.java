package sjm.examples.book.utensil;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 */

/**
 * This class harbors a few handy swing utilities.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */

public class SwingUtensil {
	/*
	 * Set the location of a frame to be the center of the screen.
	 */
	public static void center(Frame f) {
		Dimension sDim = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension fDim = f.getSize();
		if (fDim.height > sDim.height) {
			fDim.height = sDim.height;
		}
		if (fDim.width > sDim.width) {
			fDim.width = sDim.width;
		}

		f.setLocation((sDim.width - fDim.width) / 2, (sDim.height - fDim.height) / 2);
	}

	/**
	 * A standard font for interactive development environment text areas.
	 */
	public static Font ideFont() {
		int style = Font.PLAIN;
		int size = 18;
		String name = "lucida sans typewriter";
		Font f = new Font(name, style, size);
		if (!f.getFamily().equals(name)) {
			name = "Monospaced";
			f = new Font(name, style, size);
		}
		return f;
	}

	/**
	 * Return a standard text area, with the standard font and border, and with
	 * word wrapping enabled.
	 */
	public static JTextArea ideTextArea() {
		JTextArea ta = new JTextArea();
		ta.setFont(ideFont());
		ta.setBorder(new EmptyBorder(5, 5, 5, 5));
		ta.setLineWrap(true);
		return ta;
	}

	/**
	 * Return a standard titled border.
	 */
	public static TitledBorder ideTitledBorder(String title) {
		TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), title,
				TitledBorder.LEFT, TitledBorder.TOP);
		tb.setTitleColor(Color.black);
		tb.setTitleFont(ideFont());
		return tb;
	}

	/**
	 * Create a frame with the given title around the given component; center
	 * and display the frame; listen for exit, closing the frame's window on
	 * exit.
	 */
	public static JFrame launch(Component c, String title) {
		JFrame frame = new JFrame(title);
		frame.getContentPane().add(c);
		SwingUtensil.listen(frame);
		frame.pack();
		SwingUtensil.center(frame);
		frame.setVisible(true);
		return frame;
	}

	/*
	 * Set up an exit listener for a frame.
	 */
	public static void listen(Frame f) {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};
		});
	}

	/**
	 * Returns a standard text panel, with a scroll pane around a text area, and
	 * with a title border.
	 * 
	 * @param String
	 *            the panel title
	 * 
	 * @param JTextArea
	 *            the text area to wrap
	 * 
	 * @param Dimension
	 *            the preferred size for this panel
	 * 
	 * @param Dimension
	 *            the minimum size for this panel
	 * 
	 * @return a standard text panel, with a scroll pane around a text area, and
	 *         with a title border.
	 */
	public static JPanel textPanel(String title, JTextArea ta, Dimension pref, Dimension min) {

		// scroll pane around text area
		JScrollPane s1 = new JScrollPane(ta);
		s1.setPreferredSize(pref);
		s1.setMinimumSize(min);

		// titled panel that contains scrolling text area
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBorder(SwingUtensil.ideTitledBorder(title));
		p.add(s1, "Center");
		return p;
	}
}
