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
public class GreyScaleTheme implements BoardTheme {
    private static final Color stone = new Color(42, 42, 42);;

    @Override
    public Color getBoardBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getBoardOutlineColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getPocketColor() {
        return Color.darkGray;
    }

    @Override
    public Color getPocketOutlineColor() {
        return Color.lightGray;
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return new Color(254, 254, 254);
    }

    @Override
    public Color getTextColor() {
        return Color.white;
    }

    @Override
    public Color getStoneColor1() {
        return stone;
    }

    @Override
    public Color getStoneColor1Outline() {
        return Color.white;
    }

    @Override
    public Color getStoneColor2() {
        return stone;
    }

    @Override
    public Color getStoneColor2Outline() {
        return Color.white;
    }

    @Override
    public Color getStoneColor3() {
        return stone;
    }

    @Override
    public Color getStoneColor3Outline() {
        return Color.white;
    }

    @Override
    public Color getStoneColor4() {
        return stone;
    }

    @Override
    public Color getStoneColor4Outline() {
        return Color.white;
    }

    @Override
    public Font getFont() {
        return new Font("Arial", Font.PLAIN, 13);
    }
}
