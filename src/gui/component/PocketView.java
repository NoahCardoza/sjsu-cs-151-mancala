/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/14/2022
 * @assignment Mancala
 */

package gui.component;

import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RectangularShape;

/**
 * The Pocket view encapsulates the logic for drawing a pocket
 * and it's stones.
 */
public class PocketView extends JButton {
    private final ModelManager modelManager;
    private boolean active;

    /**
     * Instantiates a new Pocket view.
     *
     * @param modelManager the model manager
     */
    public PocketView(ModelManager modelManager) {
        super();
        this.modelManager = modelManager;
        setBackground(modelManager.getOptionsModel().getCurrentTheme().getPocketColor());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int size = Math.min(getWidth(), getHeight());

        RectangularShape shape = modelManager.getOptionsModel().getCurrentStyle().getPocket(
                0,
                0,
                size - 1,
                size - 1
        );

        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketColor());
        g2.fill(shape);

        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketOutlineColor());
        g2.draw(shape);

        if (active) {
            g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketActiveOutlineColor());
            g2.draw(shape);
        }
    }

    /**
     * Sets stone count.
     *
     * @param count the stone count
     */
    public void setCount(int count) {
        if (getComponents().length == count) {
            return;
        }

        for (Component c : getComponents()) {
            remove(c);
        }

        for (int i = 0; i < count; i++) {
            // TODO: choose random colors
            add(new Stone(modelManager, Stone.COLOR_1));
        }
    }

    /**
     * Sets if the pocket is active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }
}
