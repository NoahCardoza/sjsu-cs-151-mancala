/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.layout;

import java.awt.*;

public class RadialLayout implements LayoutManager {
    public RadialLayout() {}

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

        double radius = Math.min(maxWidth, maxHeight) / 6F;
        double circumference = radius * 2;
        double deltaTheta = 2 * Math.PI / nComps;
        double theta = 0;

        int maxSize = Math.min(parent.getWidth(), parent.getHeight());

        Container parentParent = parent.getParent();
        Insets parentInsert = parentParent.getInsets();

        int parentMaxWidth = parentParent.getWidth() - (parentInsert.left + parentInsert.right);
        int parentMaxHeight = parentParent.getHeight() - (parentInsert.top + parentInsert.bottom);

        parent.setBounds(
                parentInsert.left + parentMaxWidth / 2 - maxSize / 2,
                parentInsert.top + parentMaxHeight / 2  - maxSize / 2,
                maxSize, maxSize);

        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                c.setBounds(
                        (int) (parent.getWidth() / 2 - radius + Math.cos(theta) * circumference),
                        (int) (parent.getHeight() / 2 - radius + Math.sin(theta) * circumference),
                        (int) circumference, (int) circumference
                );
                theta += deltaTheta;
            }
        }
    }

    public String toString() {
        return getClass().getName() + "[]";
    }
}
