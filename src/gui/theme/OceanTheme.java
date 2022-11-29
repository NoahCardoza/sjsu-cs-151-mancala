package gui.theme;

import java.awt.Color;
import java.awt.Font;

public class OceanTheme implements BoardTheme {
	@Override
	public String getName() {
		return "Ocean";
	}

	@Override
	public Color getBoardBackgroundColor() {
		return new Color(117, 134, 210);
	}

	@Override
	public Color getBoardOutlineColor() {
		return new Color(117, 134, 210);
	}

	@Override
	public Color getPocketColor() {
		return new Color(75, 76, 80);
	}

	@Override
	public Color getPocketOutlineColor() {
		return new Color(89, 96, 205);
	}

	@Override
	public Color getPocketActiveOutlineColor() {
		return new Color(222, 127, 183);
	}

	@Override
	public Color getTextColor() {
		return new Color(89, 96, 205);
	}

	@Override
	public Color getStoneColor1() {
		return new Color(130, 132, 63);
	}

	@Override
	public Color getStoneColor1Outline() {
		return new Color(130, 132, 63);
	}

	@Override
	public Color getStoneColor2() {
		return new Color(99, 103, 69);
	}

	@Override
	public Color getStoneColor2Outline() {
		return new Color(99, 103, 69);
	}

	@Override
	public Color getStoneColor3() {
		return new Color(84, 90, 186);
	}

	@Override
	public Color getStoneColor3Outline() {
		return new Color(84, 90, 186);
	}

	@Override
	public Color getStoneColor4() {
		return new Color(45, 52, 156);
	}

	@Override
	public Color getStoneColor4Outline() {
		return new Color(45, 52, 156);
	}

	@Override
	public Font getFont() {
		return new Font("Arial", Font.BOLD, 20);
	}
}
