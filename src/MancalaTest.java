/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MancalaTest {
	public static void main(String[] args) {
		// create a new board component
		BoardComponent board = new BoardComponent(new DefaultTheme());

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(board);

		// create the main frame
		JFrame frame = new JFrame("Mancala");
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		frame.add(panel);
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// keep the aspect ratio of the screen and enforce a minimum size
		frame.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent event) {
				int minWidth = 300;
				int minHeight = 200;

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
