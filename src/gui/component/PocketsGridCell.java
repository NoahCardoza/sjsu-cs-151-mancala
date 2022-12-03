/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/14/2022
 * @assignment Mancala
 */

package gui.component;

import gui.layout.RadialLayout;
import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a cell inside the grid bag layout of pockets.
 *
 * @author Noah Cardoza
 */
public class PocketsGridCell extends JPanel {
    /**
     * The enum Variant.
     */
    public enum Variant {
        /**
         * Pockets on the top row
         */
        TOP,
        /**
         * Pockets on the bottom row
         */
        BOTTOM }
    private final JLabel countLabel;
    private final PocketView pocketView;
    private final int index;

    /**
     * Instantiates a new Pockets grid cell.
     *
     * @param modelManager the model manager for the game
     * @param prefix       the prefix for the pocket
     * @param index        the index of the pocket to display
     * @param variant      the variant of the pocket
     */
    public PocketsGridCell(ModelManager modelManager, String prefix, int index, Variant variant) {
        super();
        this.index = index;

        countLabel = new JLabel("0", SwingConstants.CENTER);
        pocketView = new PocketView(modelManager);
        pocketView.setLayout(new RadialLayout());

        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(switch (variant) {
            case TOP -> new EmptyBorder(0,0,20,0);
            case BOTTOM -> new EmptyBorder(20, 0,0,0);
        });
        add(new JLabel(String.format("%s%d", prefix, index + 1), SwingConstants.CENTER),
                switch (variant) {
                    case BOTTOM -> BorderLayout.SOUTH;
                    case TOP -> BorderLayout.NORTH;
                });
        add(pocketView, BorderLayout.CENTER);
        add(countLabel, variant.equals(Variant.BOTTOM) ? BorderLayout.NORTH : BorderLayout.SOUTH);
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        countLabel.setText(Integer.toString(count));
        pocketView.setCount(count);
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Add action listener to pocket view.
     *
     * @param listener the l
     */
    public void addActionListener(ActionListener listener) {
        pocketView.addActionListener(event -> listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pocketClick")));
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        pocketView.setActive(active);
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return pocketView.isActive();
    }
}
