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

    public OptionsView(ModelManager modelManager) {
        setBorder(new EmptyBorder(10, 10, 10, 10));

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

        add(styleSelect);
        add(themeSelect);
    }

    public void addStyleSelectedListener(ActionListener listener) {
        styleSelect.addActionListener(listener);
    }

    public void addThemeSelectedListener(ActionListener listener) {
        themeSelect.addActionListener(listener);
    }
}
