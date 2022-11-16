/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/16/2022
 * @assignment Mancala
 */

package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class InstructionsView extends JPanel {
    public InstructionsView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font defaultFont = UIManager.getFont("Label.font");
        Font titleFont = new Font(defaultFont.getName(), Font.BOLD, 36);
        Font boldFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());

        JLabel instructionsLabel = new JLabel("Instructions", SwingConstants.CENTER);
        instructionsLabel.setFont(titleFont);

        String instructionsText = "The object of this game is to collect as many gems in your mancala (the rightmost store) as possible. You and your opponent will take turns to move the gems according to the following rules";
        String[] rules = new String[] {
                "You can only move the stones on your side",
                "Each time you move, you pick up all the stones in a pit and distribute them in a counter clockwise direction to the next pit",
                "If the last stone of a move landed on your mancala, then you can move again",
                "If the last stone of a move landed on an empty pit on your side and there are some stones in the opposite pit, then the stones in the two pits will be captured in your mancala"
        };

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets.bottom = 20;
        GridBagLayout gridBagLayout = new GridBagLayout();

        JPanel innerPanel = new JPanel();
        innerPanel.setMaximumSize(new Dimension(400, 400));
        innerPanel.setLayout(gridBagLayout);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(instructionsLabel, gridBagConstraints);
        innerPanel.add(instructionsLabel);

        gridBagConstraints.gridy = 1;
        JTextArea instructions = normalizeJTextArea(new JTextArea(instructionsText));
        gridBagLayout.setConstraints(instructions, gridBagConstraints);
        innerPanel.add(instructions);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

        for (int i = 0; i < rules.length; i++) {
            JLabel ruleIndexLabel = new JLabel(String.format("%s)", i + 1));
            ruleIndexLabel.setFont(boldFont);
            JTextArea ruleTextArea = normalizeJTextArea(new JTextArea(rules[i]));

            gridBagConstraints.gridy = i + 2;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 1;
            gridBagLayout.setConstraints(ruleIndexLabel, gridBagConstraints);
            innerPanel.add(ruleIndexLabel);

            gridBagConstraints.gridy = i + 2;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.weightx = 7;
            gridBagLayout.setConstraints(ruleTextArea, gridBagConstraints);
            innerPanel.add(ruleTextArea);
        }

        add(innerPanel);
    }

    private JTextArea normalizeJTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        return textArea;
    }
}
