/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.theme;

import java.awt.*;

/**
 * An interface to define the different colors that
 * make up the board.
 *
 * @author Noah Cardoza
 */
public interface BoardTheme {
    /**
     * Gets the name of the theme.
     *
     * @return the name
     */
    String getName();

    /**
     * Get the background color of the board.
     *
     * @return the background color of the board
     */
    Color getBoardBackgroundColor();

    /**
     * Get the board outline color.
     *
     * @return the board outline color
     */
    Color getBoardOutlineColor();

    /**
     * Gets the background color of the pockets.
     *
     * @return the background color of the pockets
     */
    Color getPocketColor();

    /**
     * Gets pocket outline color.
     *
     * @return the pocket outline color
     */
    Color getPocketOutlineColor();

    /**
     * Gets the outline color of an active pocket.
     *
     * @return the outline color of an active pocket
     */
    Color getPocketActiveOutlineColor();

    /**
     * Gets the text color.
     *
     * @return the text color.
     */
    Color getTextColor();

    /**
     * Gets the first variant of the stone color
     *
     * @return the first variant of the stone color
     */
    Color getStoneColor1();

    /**
     * Gets stone color 1 outline.
     *
     * @return the stone color 1 outline
     */
    Color getStoneColor1Outline();

    /**
     * Gets the second variant of the stone color
     *
     * @return the second variant of the stone color
     */
    Color getStoneColor2();

    /**
     * Gets the stone color 2 outline.
     *
     * @return the stone color 2 outline
     */
    Color getStoneColor2Outline();

    /**
     * Gets the third variant of the stone color
     *
     * @return the third variant of the stone color
     */
    Color getStoneColor3();

    /**
     * Gets the stone color 3 outline.
     *
     * @return the stone color 3 outline
     */
    Color getStoneColor3Outline();

    /**
     * Gets the forth variant of the stone color
     *
     * @return the forth variant of the stone color
     */
    Color getStoneColor4();

    /**
     * Gets the stone color 4 outline.
     *
     * @return the stone color 4 outline
     */
    Color getStoneColor4Outline();

    /**
     * Gets the font.
     *
     * @return the font
     */
    Font getFont();
}
