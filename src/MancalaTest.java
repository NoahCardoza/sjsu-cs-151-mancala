/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import mancala.board.style.BoardStyle;
import mancala.board.style.DefaultBoardStyle;
import mancala.board.style.BlockBoardStyle;
import mancala.board.style.SquareBoardStyle;
import mancala.board.theme.BoardTheme;
import mancala.board.theme.DefaultTheme;
import mancala.board.theme.GreyscaleTheme;
import mancala.board.theme.HackerTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class MancalaTest {
	public static void main(String[] args) {
		// create a new board component
		BoardComponent board = new BoardComponent(new DefaultTheme(), new DefaultBoardStyle());

		JPanel boardPanel = new JPanel();
		boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
		boardPanel.add(board);

		// create the main frame
		JFrame frame = new JFrame("Mancala");

		frame.setSize(900, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		ArrayList<BoardStyle> styles = new ArrayList<>();
		styles.add(new DefaultBoardStyle());
		styles.add(new SquareBoardStyle());
		styles.add(new BlockBoardStyle());

		JComboBox<Object> styleSelect = new JComboBox<>(styles.stream().map(BoardStyle::getName).toArray());
		styleSelect.setSelectedIndex(0);
		styleSelect.addActionListener((event) -> {
			board.setBoardStyle(styles.get(styleSelect.getSelectedIndex()));
		});

		controlPanel.add(styleSelect);


		JButton btn = new JButton("Add Stone");
		btn.setSize(new Dimension(30,20));
		controlPanel.add(btn);

		frame.add(boardPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.PAGE_END);

		// TODO: modify to when user chooses how many stones to play with
		btn.addActionListener((event) -> {
			for (Pocket pocket : board.getPocketsPlayerA()) {
				pocket.addStone();
			}
			for (Pocket pocket : board.getPocketsPlayerB()) {
				pocket.addStone();
			}
			board.repaint();
		});

		ArrayList<BoardTheme> themes = new ArrayList<>();
		themes.add(new DefaultTheme());
		themes.add(new HackerTheme());
		themes.add(new GreyscaleTheme());

		JComboBox<Object> themeSelect = new JComboBox<>(themes.stream().map(BoardTheme::getName).toArray());
		themeSelect.setSelectedIndex(0);
		themeSelect.addActionListener((event) -> {
			board.setTheme(themes.get(themeSelect.getSelectedIndex()));
		});

		controlPanel.add(themeSelect);

		// keep the aspect ratio of the screen and enforce a minimum size
		frame.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent event) {
				int minWidth = 600;
				int minHeight = 350;

				Rectangle b = event.getComponent().getBounds();
				if (b.width < minWidth) {
					b.width = minWidth;
				}
				if (b.height < minHeight) {
					b.height = minHeight;
				}
				event.getComponent().setBounds(b.x, b.y, b.width, b.width * minHeight / minWidth);
			}
		});

		frame.setVisible(true);
	}
}
