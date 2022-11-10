/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.view;

import gui.component.Pocket;
import gui.style.*;
import gui.theme.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {
    private final BoardView board;
    private final OptionsView optionsView;

    public MainView() {
        setTitle("Mancala");

        // create a new board component
        board = new BoardView(new DefaultTheme(), new DefaultBoardStyle());
        optionsView = new OptionsView();

        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.add(board);

        JButton btn = new JButton("Add Stone");
        btn.setSize(new Dimension(30,20));

        add(boardPanel, BorderLayout.CENTER);
        add(optionsView, BorderLayout.PAGE_END);

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
    }

    public BoardView getBoard() {
        return board;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }
}
