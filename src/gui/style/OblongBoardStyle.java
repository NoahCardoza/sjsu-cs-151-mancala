package gui.style;

import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

public class OblongBoardStyle implements BoardStyle {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Oblong";
	}

	@Override
	public RectangularShape getMancala(double x, double y, double w, double h) {
		return new RoundRectangle2D.Double(x, y, w*0.9, h, 200, 20);
	}

	@Override
	public RectangularShape getPocket(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return new RoundRectangle2D.Double(x, y, w*0.9, h, 200, 20);
	}

	@Override
	public RectangularShape getStone(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return new RoundRectangle2D.Double(x, y, w, h, 200, 10);
	}

}
