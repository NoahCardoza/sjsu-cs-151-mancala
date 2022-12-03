/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.theme;

import java.awt.*;

/**
 * Defines the hacker color scheme used in the game.
 *
 * @author Noah Cardoza
 */
public class HackerTheme implements BoardTheme {
    private static final Color limeGreen = new Color(116, 255, 1);
    private static final Color black = new Color(0, 0, 0);
    private static final Color offBlack = new Color(19, 19, 19);
    private static final Color offWhite = new Color(254, 254, 254);
    private static final Color gray = new Color(52, 52, 52);
    private static final Font font = new Font("Courier New", Font.PLAIN, 13);

    @Override
    public String getName() {
        return "Hacker";
    }

    @Override
    public Color getBoardBackgroundColor() {
        return gray;
    }

    @Override
    public Color getBoardOutlineColor() {
        return black;
    }

    @Override
    public Color getPocketColor() {
        return offBlack;
    }

    @Override
    public Color getPocketOutlineColor() {
        return limeGreen;
    }

    @Override
    public Color getPocketActiveOutlineColor() {
        return offWhite;
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
        return font;
    }
}
