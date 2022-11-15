/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.component;

import gui.layout.RadialLayout;
import gui.model.ModelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PocketsGridCell extends JPanel {
    public enum Variant {
        TOP,
        BOTTOM,
    }
    private final JLabel countLabel;
    private final PocketView pocketView;
    private final int index;

    public PocketsGridCell(ModelManager modelManager, String prefix, int index, Variant variant) {
        super();
        this.index = index;

        countLabel = new JLabel("0", SwingConstants.CENTER);
        pocketView = new PocketView(modelManager);
        pocketView.setLayout(new RadialLayout());

        setOpaque(false);
        setLayout(new BorderLayout());
        add(new JLabel(String.format("%s%d", prefix, index), SwingConstants.CENTER), variant.equals(Variant.BOTTOM) ? BorderLayout.SOUTH : BorderLayout.NORTH);
        add(pocketView, BorderLayout.CENTER);
        add(countLabel, variant.equals(Variant.BOTTOM) ? BorderLayout.NORTH : BorderLayout.SOUTH);
    }

    public void setCount(int count) {
        countLabel.setText(Integer.toString(count));
        pocketView.setCount(count);
    }

    public int getIndex() {
        return index;
    }

    public void addActionListener(ActionListener l) {
        pocketView.addActionListener(event -> l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pocketClick")));
    }

}
