package mancala.board.style;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

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
