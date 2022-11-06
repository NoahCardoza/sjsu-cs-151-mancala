package mancala.board.theme;

import java.awt.Color;
import java.awt.Font;

public class OceanTheme implements BoardTheme {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Ocean";
	}

	@Override
	public Color getBoardBackgroundColor() {
		// TODO Auto-generated method stub
		return new Color(117, 134, 210);
	}

	@Override
	public Color getBoardOutlineColor() {
		// TODO Auto-generated method stub
		return new Color(117, 134, 210);
	}

	@Override
	public Color getPocketColor() {
		// TODO Auto-generated method stub
		return new Color(75, 76, 80);
	}

	@Override
	public Color getPocketOutlineColor() {
		// TODO Auto-generated method stub
		return new Color(89, 96, 205);
	}

	@Override
	public Color getPocketActiveOutlineColor() {
		// TODO Auto-generated method stub
		return new Color(89, 96, 205);
	}

	@Override
	public Color getTextColor() {
		// TODO Auto-generated method stub
		return new Color(89, 96, 205);
	}

	@Override
	public Color getStoneColor1() {
		// TODO Auto-generated method stub
		return new Color(130, 132, 63);
	}

	@Override
	public Color getStoneColor1Outline() {
		// TODO Auto-generated method stub
		return new Color(130, 132, 63);
	}

	@Override
	public Color getStoneColor2() {
		// TODO Auto-generated method stub
		return new Color(99, 103, 69);
	}

	@Override
	public Color getStoneColor2Outline() {
		// TODO Auto-generated method stub
		return new Color(99, 103, 69);
	}

	@Override
	public Color getStoneColor3() {
		// TODO Auto-generated method stub
		return new Color(84, 90, 186);
	}

	@Override
	public Color getStoneColor3Outline() {
		// TODO Auto-generated method stub
		return new Color(84, 90, 186);
	}

	@Override
	public Color getStoneColor4() {
		// TODO Auto-generated method stub
		return new Color(45, 52, 156);
	}

	@Override
	public Color getStoneColor4Outline() {
		// TODO Auto-generated method stub
		return new Color(45, 52, 156);
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return new Font("Arial", Font.BOLD, 20);
	}

}
