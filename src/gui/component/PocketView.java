/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.component;

import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RectangularShape;

public class PocketView extends JButton {
    private final ModelManager modelManager;

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
    }

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
}