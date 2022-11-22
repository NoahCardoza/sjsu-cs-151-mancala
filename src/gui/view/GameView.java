/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.view;

import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameView extends JPanel {
    private final BoardView boardView;
    private final OptionsView optionsView;

    public GameView(ModelManager modelManager) {
        // create a new board component
        boardView = new BoardView(modelManager);
        optionsView = new OptionsView(modelManager);

        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.add(boardView);
        setBackground(Color.PINK);

        add(boardPanel, BorderLayout.CENTER);
        add(optionsView, BorderLayout.PAGE_END);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }
}
