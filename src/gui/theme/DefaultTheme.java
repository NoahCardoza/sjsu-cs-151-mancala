/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.theme;

import java.awt.*;

/**
 * Defines the default color scheme used in the game.
 */
public class DefaultTheme implements BoardTheme {
    public static final Color transparent = new Color(0, 0, 0, 0);
    public static final Color lightBrown = new Color(166, 124, 82);
    public static final Color darkBrown = new Color(117, 76, 37);
    public static final Color mustardYellow = new Color(251, 176, 60);
    public static final Color rubyRed = new Color(212, 19, 89);
    public static final Color emeraldGreen = new Color(34, 181, 116);
    public static final Color babyBlue = new Color(41, 171, 226);
    public static final Color offWhite = new Color(254, 254, 254);
    public static final Color textColor = new Color(54, 53, 53, 147);

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public Color getBoardBackgroundColor() {
        return lightBrown;
    }

    @Override
    public Color getBoardOutlineColor() {
        return transparent;
    }

    @Override
    public Color getPocketColor() {
        return darkBrown;
    }

    @Override
    public Color getPocketOutlineColor() {
        return transparent;
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return offWhite;
    }

    @Override
    public Color getTextColor() {
        return textColor;
    }

    @Override
    public Color getStoneColor1() {
        return babyBlue;
    }

    @Override
    public Color getStoneColor1Outline() {
        return transparent;
    }

    @Override
    public Color getStoneColor2() {
        return emeraldGreen;
    }

    @Override
    public Color getStoneColor2Outline() {
        return transparent;
    }

    @Override
    public Color getStoneColor3() {
        return rubyRed;
    }

    @Override
    public Color getStoneColor3Outline() {
        return transparent;
    }

    @Override
    public Color getStoneColor4() {
        return mustardYellow;
    }

    @Override
    public Color getStoneColor4Outline() {
        return transparent;
    }

    @Override
    public Font getFont() {
        return new Font("Arial", Font.PLAIN, 13);
    }
}
