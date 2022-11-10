/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package mancala.board.style;

import java.awt.geom.RectangularShape;

public interface BoardStyle {
    String getName();
    RectangularShape getMancala(double x, double y, double w, double h);
    RectangularShape getPocket(double x, double y, double w, double h);
    RectangularShape getStone(double x, double y, double w, double h);
}
