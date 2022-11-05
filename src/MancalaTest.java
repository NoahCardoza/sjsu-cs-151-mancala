/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import mancala.board.style.DefaultBoardStyle;
import mancala.board.style.HardEdgesBoardStyle;
import mancala.board.style.SquareBoardStyle;
import mancala.board.theme.DefaultTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MancalaTest {
	public static void main(String[] args) {
		// create a new board component
//		BoardComponent board = new BoardComponent(new DefaultTheme(), new DefaultBoardStyle());
//		BoardComponent board = new BoardComponent(new mancala.board.theme.DefaultTheme(), new SquareBoardStyle());
//		BoardComponent board = new BoardComponent(new mancala.board.theme.HackerTheme(), new HardEdgesBoardStyle());
//		BoardComponent board = new BoardComponent(new mancala.board.theme.HackerTheme(), new DefaultBoardStyle());
		BoardComponent board = new BoardComponent(new mancala.board.theme.GreyScaleTheme(), new SquareBoardStyle());




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
			board.revalidate();
		});


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
