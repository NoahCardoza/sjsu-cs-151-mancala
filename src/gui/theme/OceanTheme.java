/**
 * @author Dominic Lopez
 * @version 0.0.1
 * @date 12/2/2022
 * @assignment Mancala
 */
package gui.theme;

import java.awt.Color;
import java.awt.Font;

/**
 * Ocean theme strategy for coloring the board.
 *
 * @author Dominic Lopez
 * @author Noah Cardoza
 */
public class OceanTheme implements BoardTheme {
	
	/**
	 * Predefined colors for use in Ocean theme
	 */
	private static final Color boardBackgroundColor = new Color(117, 134, 210);
	private static final Color pocketColor = new Color(75, 76, 80);
	private static final Color deepPurple = new Color(89, 96, 205);
	private static final Color darkPurple = new Color(84, 90, 186);
	private static final Color darkBlue = new Color(45, 52, 156);
	private static final Color greenMud = new Color(130, 132, 63);
	private static final Color armyGreen = new Color(99, 103, 69);
	private static final Font font = new Font("Arial", Font.BOLD, 20);

	/**
	 * Name of the Ocean theme.
	 * 
	 * @return Returns the name of the Ocean theme.
	 */
	@Override
	public String getName() {
		return "Ocean";
	}

	/**
	 * Background color of Ocean theme.
	 * 
	 * @return Returns color of the Ocean theme.
	 */
	@Override
	public Color getBoardBackgroundColor() {
		return boardBackgroundColor;
	}

	
	
	/**
	 * Outline color of Ocean theme.
	 * 
	 * @return Returns outline color of the Ocean theme.
	 */
	@Override
	public Color getBoardOutlineColor() {
		return boardBackgroundColor;
	}


	/**
	 * Pocket color of Ocean theme.
	 * 
	 * @return Returns pocket color of the Ocean theme.
	 */
	@Override
	public Color getPocketColor() {
		return pocketColor;
	}

	/**
	 * Pocket outline color of Ocean theme.
	 * 
	 * @return Returns pocket outline color of the Ocean theme.
	 */
	@Override
	public Color getPocketOutlineColor() {
		return deepPurple;
	}

	/**
	 * Active pocket outline color of Ocean theme.
	 * 
	 * @return Returns active pocket outline color of the Ocean theme.
	 */
	@Override
	public Color getPocketActiveOutlineColor() {
		return new Color(222, 127, 183);
	}

	/**
	 * Text color of Ocean theme.
	 * 
	 * @return Returns text color of the Ocean theme.
	 */
	@Override
	public Color getTextColor() {
		return deepPurple;
	}

	/**
	 * Stone color of Ocean theme.
	 * 
	 * @return Returns stone color of the Ocean theme.
	 */
	@Override
	public Color getStoneColor1() {
		return greenMud;
	}

	/**
	 * Stone outline color of Ocean theme.
	 * 
	 * @return Returns stone outline color of the Ocean theme.
	 */
	@Override
	public Color getStoneColor1Outline() {
		return greenMud;
	}

	/**
	 * Stone color 2 of Ocean theme.
	 * 
	 * @return Returns stone color 2 of the Ocean theme.
	 */
	@Override
	public Color getStoneColor2() {
		return armyGreen;
	}

	/**
	 * Stone color 2 outline of Ocean theme.
	 * 
	 * @return Returns stone color 2 outline of the Ocean theme.
	 */
	@Override
	public Color getStoneColor2Outline() {
		return armyGreen;
	}

	/**
	 * Stone color 3 of Ocean theme.
	 * 
	 * @return Returns stone color 3 of the Ocean theme.
	 */
	@Override
	public Color getStoneColor3() {
		return darkPurple;
	}

	/**
	 * Stone color 3 outline of Ocean theme.
	 * 
	 * @return Stone color 3 outline of the Ocean theme.
	 */
	@Override
	public Color getStoneColor3Outline() {
		return darkPurple;
	}

	/**
	   Stone color 4 of Ocean theme.
	 * 
	 * @return Returns stone color 4 of the Ocean theme.

	 */
	@Override
	public Color getStoneColor4() {
		return darkBlue;
	}

	/**
	   Stone color 4 outline of Ocean theme.
	 * 
	 * @return Returns stone color 4 outline of the Ocean theme.

	 */
	@Override
	public Color getStoneColor4Outline() {
		return darkBlue;
	}

	/**
	 * Font of Ocean theme.
	 * 
	 * @return Returns font of the Ocean theme.
	 */
	@Override
	public Font getFont() {
		return font;
	}
}
