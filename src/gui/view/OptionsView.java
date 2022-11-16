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

    public OptionsView(ModelManager modelManager) {
        setBorder(new EmptyBorder(10, 10, 10, 10));

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

        add(undoButton);
        add(styleSelect);
        add(themeSelect);
        add(nextTurnButton);
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
