/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.component;

import gui.layout.StackLayout;
import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RectangularShape;

public class MancalaView extends JPanel {
    private final ModelManager modelManager;
    public MancalaView(ModelManager modelManager, int index) {
        super();

        this.modelManager = modelManager;

        setBorder(new EmptyBorder(5,5,5,5));
        setOpaque(false);

        setLayout(new StackLayout());
        modelManager.getMancalaModel().addEventListener("update:pits", (event) -> {
            int[] pits = modelManager.getMancalaModel().getPits();
            setCount(pits[index]);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        RectangularShape mancalaBox = modelManager.getOptionsModel().getCurrentStyle().getMancala(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1
        );

        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketColor());
        g2.fill(mancalaBox);
        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketOutlineColor());
        g2.draw(mancalaBox);
    }

    public void setCount(int count) {
        removeAll();

        for (int i = 0; i < count; i++) {
            // TODO: choose random colors
            add(new Stone(modelManager, Stone.COLOR_1));
        }

        repaint();
    }
}
