package gui.theme;

import java.awt.Color;
import java.awt.Font;

public class OceanTheme implements BoardTheme {
	private static final Color boardBackgroundColor = new Color(117, 134, 210);
	private static final Color pocketColor = new Color(75, 76, 80);
	private static final Color deepPurple = new Color(89, 96, 205);
	private static final Color darkPurple = new Color(84, 90, 186);
	private static final Color darkBlue = new Color(45, 52, 156);
	private static final Color greenMud = new Color(130, 132, 63);
	private static final Color armyGreen = new Color(99, 103, 69);
	private static final Font font = new Font("Arial", Font.BOLD, 20);

	@Override
	public String getName() {
		return "Ocean";
	}

	@Override
	public Color getBoardBackgroundColor() {
		return boardBackgroundColor;
	}

	@Override
	public Color getBoardOutlineColor() {
		return boardBackgroundColor;
	}


	@Override
	public Color getPocketColor() {
		return pocketColor;
	}

	@Override
	public Color getPocketOutlineColor() {
		return deepPurple;
	}

	@Override
	public Color getPocketActiveOutlineColor() {
		return new Color(222, 127, 183);
	}

	@Override
	public Color getTextColor() {
		return deepPurple;
	}

	@Override
	public Color getStoneColor1() {
		return greenMud;
	}

	@Override
	public Color getStoneColor1Outline() {
		return greenMud;
	}

	@Override
	public Color getStoneColor2() {
		return armyGreen;
	}

	@Override
	public Color getStoneColor2Outline() {
		return armyGreen;
	}

	@Override
	public Color getStoneColor3() {
		return darkPurple;
	}

	@Override
	public Color getStoneColor3Outline() {
		return darkPurple;
	}

	@Override
	public Color getStoneColor4() {
		return darkBlue;
	}

	@Override
	public Color getStoneColor4Outline() {
		return darkBlue;
	}

	@Override
	public Font getFont() {
		return font;
	}
}
