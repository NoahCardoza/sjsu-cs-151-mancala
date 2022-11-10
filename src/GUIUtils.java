/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A collection of instance unspecific methods for dealing with the GUI.
 */
public class GUIUtils {
    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     *
     * @link <a href="https://stackoverflow.com/a/27711103/6169961">StackOverflow</a>
     */
    public static void drawCenteredString(Graphics2D g, String text, Rectangle2D.Float rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = (int) ((int) rect.x + (rect.width - metrics.stringWidth(text)) / 2);
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = (int) ((int) rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent());
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
