/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OptionsView extends JPanel {
    private final JComboBox<Object> styleSelect;
    private final JComboBox<Object> themeSelect;

    public OptionsView() {
        setBorder(new EmptyBorder(10, 10, 10, 10));

        styleSelect = new JComboBox<>();
        themeSelect = new JComboBox<>();

        add(styleSelect);
        add(themeSelect);
    }

    public JComboBox<Object> getStyleSelect() {
        return styleSelect;
    }

    public JComboBox<Object> getThemeSelect() {
        return themeSelect;
    }
}
