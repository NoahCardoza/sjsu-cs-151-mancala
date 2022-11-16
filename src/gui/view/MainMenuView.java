/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {
    private final JComboBox<String> mancalaCountSelect;
    private final JButton startGameButton;

    public MainMenuView() {
        setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] options = new String[] {"1", "2", "3", "4"};
        mancalaCountSelect = new JComboBox<>(options);

        startGameButton = new JButton("Start Game");
        startGameButton.setSize(new Dimension(30,20));

        add(mancalaCountSelect);
        add(startGameButton);
    }

    public int getMancalaCount() {
        return mancalaCountSelect.getSelectedIndex() + 1;
    }

    public void addStartGameButtonListener(ActionListener l) {
        startGameButton.addActionListener(l);
    }
}
