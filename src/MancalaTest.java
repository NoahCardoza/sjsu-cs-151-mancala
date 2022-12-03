/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */


import gui.GUI;

import javax.swing.*;

/**
 * The main class for the mancala game.
 *
 * @author Noah Cardoza
 */
public class MancalaTest {
	/**
	 * The entry point of application.
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(GUI::new);
	}
}
