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
public class HackerTheme implements BoardTheme {
    private static final Color limeGreen = new Color(116, 255, 1);
    private static final Color black = new Color(0, 0, 0);;

    @Override
    public String getName() {
        return "Hacker";
    }

    @Override
    public Color getBoardBackgroundColor() {
        return new Color(52, 52, 52);
    }

    @Override
    public Color getBoardOutlineColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getPocketColor() {
        return new Color(19, 19, 19);
    }

    @Override
    public Color getPocketOutlineColor() {
        return limeGreen;
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return new Color(254, 254, 254);
    }

    @Override
    public Color getTextColor() {
        return limeGreen;
    }

    @Override
    public Color getStoneColor1() {
        return black;
    }

    @Override
    public Color getStoneColor1Outline() {
        return limeGreen;
    }

    @Override
    public Color getStoneColor2() {
        return black;
    }

    @Override
    public Color getStoneColor2Outline() {
        return limeGreen;
    }

    @Override
    public Color getStoneColor3() {
        return black;
    }

    @Override
    public Color getStoneColor3Outline() {
        return limeGreen;
    }

    @Override
    public Color getStoneColor4() {
        return black;
    }

    @Override
    public Color getStoneColor4Outline() {
        return limeGreen;
    }

    @Override
    public Font getFont() {
        return new Font("Arial", Font.PLAIN, 13);
    }
}
