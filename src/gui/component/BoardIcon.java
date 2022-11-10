/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.component;

import java.awt.*;

/**
 * An interface to define different parts of the board
 */
public interface BoardIcon {
    /**
     * Draw with x and y defaulting to (0, 0).
     *
     * @param g2 the graphics object to draw on
     */
    default void draw(Graphics2D g2) {
        draw(g2, 0,0);
    }

    /**
     * Render the object to the graphics context.
     *
     * @param g2 the graphics object to draw on
     */
    void draw(Graphics2D g2, int x, int y);

    /**
     * Hook called whenever the screen resizes.
     *
     * @param width the new width of the board
     * @param height the new height of the board
     */
    void onResize(int width, int height);
}
