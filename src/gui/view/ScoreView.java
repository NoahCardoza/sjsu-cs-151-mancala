/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/16/2022
 * @assignment Mancala
 */

package gui.view;

import gui.model.ModelManager;
import gui.style.BoardStyle;
import gui.theme.BoardTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The final Score view.
 *
 * @author Noah Cardoza
 */
public class ScoreView extends JPanel {

    /**
     * Instantiates a new Score view.
     *
     * @param modelManager the model manager
     */
    public ScoreView(ModelManager modelManager) {
        setLayout(new BorderLayout());

        JLabel playerScoreLabelA = new JLabel();
        JLabel playerScoreLabelB = new JLabel();

        add(new JLabel("Score:"), BorderLayout.NORTH);

        add(playerScoreLabelA, BorderLayout.CENTER);
        add(playerScoreLabelB, BorderLayout.SOUTH);

        modelManager.getMancalaModel().addEventListener("update:pits", (event) -> {
            int[] pits = modelManager.getMancalaModel().getPits();
            playerScoreLabelA.setText(String.format("Player A: %s", pits[6]));
            playerScoreLabelB.setText(String.format("Player B: %s", pits[13]));
        }, true);

    }
}
