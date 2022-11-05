package mancala.board.style;

import mancala.board.style.BoardStyle;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

public class DefaultBoardStyle implements BoardStyle {
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
