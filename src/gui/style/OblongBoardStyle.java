/**
 * @author Dominic Lopez
 * @version 0.0.1
 * @date 12/2/2022
 * @assignment Mancala
 */
package gui.style;

import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

public class OblongBoardStyle implements BoardStyle {
	@Override
	public String getName() {
		return "Oblong";
	}

	// TODO: some how increase padding of parents so children don't overflow with skinny rectangles
	@Override
	public RectangularShape getMancala(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x + w * 0.1 / 2, y, w*0.9, h, 200, 20);
	}

	@Override
	public RectangularShape getPocket(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x + w * 0.1 / 2, y, w*0.9, h, 200, 20);
	}

	@Override
	public RectangularShape getStone(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x, y, w, h, 200, 10);
	}

}
