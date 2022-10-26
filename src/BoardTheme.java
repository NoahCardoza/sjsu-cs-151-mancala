/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;

/**
 * An interface to define the different colors that
 * make up the board.
 */
public interface BoardTheme {
    /**
     * Defines the background color of the board.
     *
     * @return the background color of the board
     */
    Color getBoardBackgroundColor();

    /**
     * Defines the background of the pockets.
     *
     * @return the background color of the pockets
     */
    Color getPocketColor();

    /**
     * Defines the outline color of an active pocket.
     *
     * @return the outline color of an active pocket
     */
    Color getPocketOutlineColor();

    /**
     * Defines the text color.
     *
     * @return the text color.
     */
    Color getTextColor();

    /**
     * Defines the first variant of the stone color
     *
     * @return the first variant of the stone color
     */
    Color getStoneColor1();

    /**
     * Defines the second variant of the stone color
     *
     * @return the second variant of the stone color
     */
    Color getStoneColor2();

    /**
     * Defines the third variant of the stone color
     *
     * @return the third variant of the stone color
     */
    Color getStoneColor3();

    /**
     * Defines the forth variant of the stone color
     *
     * @return the forth variant of the stone color
     */
    Color getStoneColor4();

    Font getFont();
}
