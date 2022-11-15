/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.component;

import gui.model.ModelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RectangularShape;

public class Stone extends JLabel {
    public final static int COLOR_1 = 0;
    public final static int COLOR_2 = 1;
    public final static int COLOR_3 = 2;
    public final static int COLOR_4 = 3;

    private boolean active;
    private Color backgroundColor;
    private Color borderColor;


    private RectangularShape stoneEllipse;

    public Stone(ModelManager modelManager, int variant) {
        this.active = false;

        modelManager.getOptionsModel().addEventListener("update:currentTheme", (event) -> {
            this.backgroundColor = switch (variant) {
                case COLOR_1 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor1();
                case COLOR_2 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor2();
                case COLOR_3 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor3();
                case COLOR_4 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor4();
                default -> throw new IllegalStateException("Unexpected Stone variant value: " + variant);
            };

            this.borderColor = switch (variant) {
                case COLOR_1 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor1Outline();
                case COLOR_2 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor2Outline();
                case COLOR_3 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor3Outline();
                case COLOR_4 -> modelManager.getOptionsModel().getCurrentTheme().getStoneColor4Outline();
                default -> throw new IllegalStateException("Unexpected Stone variant value: " + variant);
            };
        }, true);

        setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                int size = Math.min(getHeight(), getWidth());

                stoneEllipse = modelManager.getOptionsModel().getCurrentStyle().getStone(
                        0,
                        0,
                        size - 1,
                        size - 1
                );

                g2.setColor(getBackgroundColor());
                g2.fill(stoneEllipse);

                if (active) {
                    g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getPocketActiveOutlineColor());
                } else {
                    g2.setColor(getBorderColor());
                }
                g2.draw(stoneEllipse);
            }

            @Override
            public int getIconWidth() {
                return getWidth();
            }

            @Override
            public int getIconHeight() {
                return getHeight();
            }
        });
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
