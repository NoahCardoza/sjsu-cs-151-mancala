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
    private final JLabel currentPlayerLable;

    public OptionsView(ModelManager modelManager) {
        setBorder(new EmptyBorder(10, 10, 10, 10));

        mainMenuButton = new JButton("Main Menu");

        undoButton = new JButton("Undo");
        undoButton.setEnabled(false);
        modelManager.getMancalaModel().add(event -> {
            undoButton.setEnabled(modelManager.getMancalaModel().getCanUndo());
        });

        nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setEnabled(false);
        modelManager.getMancalaModel().add(event -> {
            nextTurnButton.setEnabled(modelManager.getMancalaModel().getCanProceedToNextTurn());
        });

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

        currentPlayerLable = new JLabel("");
        currentPlayerLable.setText(modelManager.getMancalaModel().getCurrentPlayer().toString());
        modelManager.getMancalaModel().add((event) -> {
            currentPlayerLable.setText(modelManager.getMancalaModel().getCurrentPlayer().toString());
        });

        add(mainMenuButton);
        add(undoButton);
        add(styleSelect);
        add(themeSelect);
        add(nextTurnButton);
        add(currentPlayerLable);
    }

    public void addBackToMainMenuActionListener(ActionListener listener) {
        mainMenuButton.addActionListener(listener);
    }

    public void addUndoActionListener(ActionListener listener) {
        undoButton.addActionListener(listener);
    }

    public void addNextTurnActionListener(ActionListener listener) {
        undoButton.addActionListener(listener);
    }

    public void addStyleSelectedListener(ActionListener listener) {
        styleSelect.addActionListener(listener);
    }

    public void addThemeSelectedListener(ActionListener listener) {
        themeSelect.addActionListener(listener);
    }
}
