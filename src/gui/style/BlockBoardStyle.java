/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.style;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * The block board style.
 */
public class BlockBoardStyle implements BoardStyle {
    @Override
    public String getName() {
        return "Block";
    }

    @Override
    public RectangularShape getMancala(double x, double y, double w, double h) {
        return new Rectangle2D.Double(x, y, w, h);
    }

    @Override
    public RectangularShape getPocket(double x, double y, double w, double h) {
        return new Rectangle2D.Double(x, y, w, h);
    }

    @Override
    public RectangularShape getStone(double x, double y, double w, double h) {
        return new Rectangle2D.Double(x, y, w, h);
    }
}
