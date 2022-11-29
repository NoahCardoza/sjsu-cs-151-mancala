/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.view;

import gui.model.ModelManager;
import gui.style.BoardStyle;
import gui.theme.BoardTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class OptionsView extends JPanel {
    private final JComboBox<Object> styleSelect;
    private final JComboBox<Object> themeSelect;
    private final JButton undoButton;
    private final JButton nextTurnButton;
    private final JButton mainMenuButton;
    private final JLabel currentPlayerLabel;

    public OptionsView(ModelManager modelManager) {
        setBorder(new EmptyBorder(10, 10, 10, 10));

        mainMenuButton = new JButton("Main Menu");
        undoButton = new JButton();
        nextTurnButton = new JButton("End Turn");
        currentPlayerLabel = new JLabel();

        // listeners for undo button
        modelManager.getMancalaModel().addEventListener("update:canUndo", event -> {
            undoButton.setEnabled(modelManager.getMancalaModel().getCanUndo());
        }, true);

        modelManager.getMancalaModel().addEventListener("update:undosAvailable", event -> {
            undoButton.setText(String.format("Undo (%s)", modelManager.getMancalaModel().getUndosAvailable()));
        }, true);

        // listeners for end turn button
        modelManager.getMancalaModel().addEventListener("update:canEndTurn", event -> {
            nextTurnButton.setEnabled(modelManager.getMancalaModel().getCanEndTurn());
        }, true);

        // configure values for combo boxes
        styleSelect = new JComboBox<>(
                modelManager
                        .getOptionsModel()
                        .getStyles()
                        .stream()
                        .map(BoardStyle::getName)
                        .toArray()
        );

        themeSelect = new JComboBox<>(
                modelManager
                        .getOptionsModel()
                        .getThemes()
                        .stream()
                        .map(BoardTheme::getName)
                        .toArray()
        );

        // listen for current player label
        modelManager.getMancalaModel().addEventListener("update:currentPlayer", (event) -> {
            currentPlayerLabel.setText(String.format(
                    "%s's Turn",
                    switch (modelManager.getMancalaModel().getCurrentPlayer()) {
                        case PLAYER_ONE -> "Player A";
                        case PLAYER_TWO -> "Player B";
                        case TIE -> null; // should never happen
                    }));
        }, true);

        // add all components to the view
        add(new ScoreView(modelManager));
        add(mainMenuButton);
        add(undoButton);
        add(styleSelect);
        add(themeSelect);
        add(nextTurnButton);
        add(currentPlayerLabel);
    }

    public void addBackToMainMenuActionListener(ActionListener listener) {
        mainMenuButton.addActionListener(listener);
    }

    public void addUndoActionListener(ActionListener listener) {
        undoButton.addActionListener(listener);
    }

    public void addNextTurnActionListener(ActionListener listener) {
        nextTurnButton.addActionListener(listener);
    }

    public void addStyleSelectedListener(ActionListener listener) {
        styleSelect.addActionListener(listener);
    }

    public void addThemeSelectedListener(ActionListener listener) {
        themeSelect.addActionListener(listener);
    }
}
