/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.view;

import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {
    private final BoardView board;
    private final OptionsView optionsView;

    public MainView(ModelManager modelManager) {

        setTitle("Mancala");

        // create a new board component
        board = new BoardView(modelManager);
        optionsView = new OptionsView();

        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.add(board);

        JButton btn = new JButton("Add Stone");
        btn.setSize(new Dimension(30,20));

        add(boardPanel, BorderLayout.CENTER);
        add(optionsView, BorderLayout.PAGE_END);
    }

    public BoardView getBoard() {
        return board;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }
}
