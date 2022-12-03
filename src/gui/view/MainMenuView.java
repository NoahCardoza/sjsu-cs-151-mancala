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

/**
 * The main menu view.
 *
 * @author Noah Cardoza
 */
public class MainMenuView extends JPanel {
    private final JComboBox<String> mancalaCountSelect;
    private final JButton startGameButton;

    /**
     * Instantiates a new main menu view.
     */
    public MainMenuView() {
        setBorder(new EmptyBorder(20, 0, 20, 0));
        setLayout(new BorderLayout());

        JPanel footerPanel = new JPanel();

        String[] options = new String[] {"1", "2", "3", "4"};
        mancalaCountSelect = new JComboBox<>(options);
        mancalaCountSelect.setSelectedIndex(3);

        startGameButton = new JButton("Start Game");
        startGameButton.setSize(new Dimension(30,20));

        footerPanel.add(new JLabel("Number of stones: "));
        footerPanel.add(mancalaCountSelect);
        footerPanel.add(startGameButton);

        add(new InstructionsView(), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Gets mancala count.
     *
     * @return the mancala count
     */
    public int getMancalaCount() {
        return mancalaCountSelect.getSelectedIndex() + 1;
    }

    /**
     * Add start game button listener.
     *
     * @param listener the listener
     */
    public void addStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }
}
