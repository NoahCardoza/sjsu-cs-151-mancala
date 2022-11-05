/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package mancala.board.theme;

import java.awt.*;

/**
 * Defines the default color scheme used in the game.
 */
public class DefaultTheme implements BoardTheme {
    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public Color getBoardBackgroundColor() {
        return new Color(166,124, 82);
    }

    @Override
    public Color getBoardOutlineColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getPocketColor() {
        return new Color(117, 76, 37);
    }

    @Override
    public Color getPocketOutlineColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return new Color(254, 254, 254);
    }

    @Override
    public Color getTextColor() {
        return new Color(54, 53, 53, 147);
    }

    @Override
    public Color getStoneColor1() {
        return new Color(41, 171, 226);
    }

    @Override
    public Color getStoneColor1Outline() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getStoneColor2() {
        return new Color(34, 181,116);
    }

    @Override
    public Color getStoneColor2Outline() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getStoneColor3() {
        return new Color(212,19,89);
    }

    @Override
    public Color getStoneColor3Outline() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getStoneColor4() {
        return new Color(251, 176, 60);
    }

    @Override
    public Color getStoneColor4Outline() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Font getFont() {
        return new Font("Arial", Font.PLAIN, 13);
    }
}
