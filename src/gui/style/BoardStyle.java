/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.style;

import java.awt.geom.RectangularShape;

/**
 * Interface for board styles.
 *
 * @author Noah Cardoza
 */
public interface BoardStyle {
    /**
     * Gets name of the style.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the mancala shape.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w width
     * @param h height
     * @return the mancala
     */
    RectangularShape getMancala(double x, double y, double w, double h);

    /**
     * Gets the pocket shape.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w width
     * @param h height
     * @return the pocket
     */
    RectangularShape getPocket(double x, double y, double w, double h);

    /**
     * Gets the stone shape.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w width
     * @param h height
     * @return the stone
     */
    RectangularShape getStone(double x, double y, double w, double h);
}
