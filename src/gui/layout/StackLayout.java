/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/14/2022
 * @assignment Mancala
 */

package gui.layout;

import java.awt.*;

/**
 * Stacks child components from left to right until the end of
 * the parent and proceeds on the next row.
 *
 * @author Noah Cardoza
 */
public class StackLayout implements LayoutManager {
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth() - (insets.left + insets.right);
        int maxHeight = parent.getHeight() - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();

        int rows = 12;
        int itemsPerRow = 4;

        int deltaX = maxWidth / itemsPerRow;
        int deltaY = maxHeight / rows;

        deltaX = Math.min(deltaX, deltaY);
        deltaY = deltaX;

        if (deltaY <= 0) {
            return;
        }

        int x = insets.left;
        int y = insets.top + (maxHeight % deltaY) / 2 - deltaY;

        for (int i = 0 ; i < nComps ; i++) {
            if (i % itemsPerRow == 0) {
                y += deltaY;
                x = insets.left;
            }

            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                c.setBounds(
                        x,
                        y,
                        deltaX,
                        deltaY
                );
                x += deltaX;
            }
        }
    }

    public String toString() {
        return getClass().getName() + "[]";
    }
}
