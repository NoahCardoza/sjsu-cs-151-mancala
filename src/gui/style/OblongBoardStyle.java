/**
 * @author Dominic Lopez
 * @version 0.0.1
 * @date 12/2/2022
 * @assignment Mancala
 */
package gui.style;

import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

/**
 * Oblong board style strategy for designing the board shape.
 *
 * @author Dominic Lopez
 * @author Noah Cardoza
 */
public class OblongBoardStyle implements BoardStyle {
	
	/**
	 * Name of the oblong board style
	 * 
	 * @return Name of the oblong board style
	 */
	@Override
	public String getName() {
		return "Oblong";
	}

	/**
	 * Oval mancala shape for Oblong style
	 * 
	 * @return Oval mancala shape
	 */
	@Override
	public RectangularShape getMancala(double x, double y, double w, double h) {
		// TODO: some how increase padding of parents so children don't overflow with skinny rectangles
		return new RoundRectangle2D.Double(x + w * 0.1 / 2, y, w*0.9, h, 200, 20);
	}

	/**
	 * Oval pocket shape for Oblong style
	 * 
	 * @return Oval pocket shape
	 */
	@Override
	public RectangularShape getPocket(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x + w * 0.1 / 2, y, w*0.9, h, 200, 20);
	}

	/**
	 * Simple circle shape for Oblong style
	 * 
	 * @return Circle stone shape
	 */
	@Override
	public RectangularShape getStone(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x, y, w, h, 200, 10);
	}

}
