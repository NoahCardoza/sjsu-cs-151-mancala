/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.style;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

/**
 * The default board style.
 *
 * @author Noah Cardoza
 */
public class DefaultBoardStyle implements BoardStyle {
    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public RectangularShape getMancala(double x, double y, double w, double h) {
        return new RoundRectangle2D.Double(x, y, w, h, 10, 10);
    }

    @Override
    public RectangularShape getPocket(double x, double y, double w, double h) {
        return new Ellipse2D.Double(x, y, w, h);
    }

    @Override
    public RectangularShape getStone(double x, double y, double w, double h) {
        return new Ellipse2D.Double(x, y, w, h);
    }
}
