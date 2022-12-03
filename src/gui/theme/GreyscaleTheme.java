/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.theme;

import java.awt.*;

/**
 * Defines the grey scale color scheme used in the game.
 *
 * @author Noah Cardoza
 */
public class GreyscaleTheme implements BoardTheme {
    private static final Color stone = new Color(42, 42, 42);
    private static final Color transparent = new Color(0, 0, 0, 0);
    private static final Color white = Color.white;
    private static final Color black = Color.black;
    private static final Color offWhite = new Color(254, 254, 254);
    private static final Color lightGray = Color.lightGray;
    private static final Color darkGray = Color.darkGray;
    private static final Font font = new Font("Arial", Font.PLAIN, 13);

    @Override
    public String getName() {
        return "Grayscale";
    }

    @Override
    public Color getBoardBackgroundColor() {
        return black;
    }

    @Override
    public Color getBoardOutlineColor() {
        return transparent;
    }

    @Override
    public Color getPocketColor() {
        return darkGray;
    }

    @Override
    public Color getPocketOutlineColor() {
        return lightGray;
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return offWhite;
    }

    @Override
    public Color getTextColor() {
        return white;
    }

    @Override
    public Color getStoneColor1() {
        return stone;
    }

    @Override
    public Color getStoneColor1Outline() {
        return white;
    }

    @Override
    public Color getStoneColor2() {
        return stone;
    }

    @Override
    public Color getStoneColor2Outline() {
        return white;
    }

    @Override
    public Color getStoneColor3() {
        return stone;
    }

    @Override
    public Color getStoneColor3Outline() {
        return white;
    }

    @Override
    public Color getStoneColor4() {
        return stone;
    }

    @Override
    public Color getStoneColor4Outline() {
        return white;
    }

    @Override
    public Font getFont() {
        return font;
    }
}
