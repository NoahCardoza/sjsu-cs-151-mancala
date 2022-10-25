/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * A component that draws a resizable mancala board.
 */
public class BoardComponent extends JComponent {
    BoardTheme theme;
    private final int aspectRatioWidth = 22;
    private final int aspectRatioHeight = 9;

    /**
     * Constructs a mancala game board from a theme
     * @param theme
     */
    public BoardComponent(BoardTheme theme) {
        this.theme = theme;

        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent event) {
                // scale the aspect ratio to set a realistic minimum
                int minWidth = aspectRatioWidth * 25;
                int minHeight = aspectRatioHeight * 25;

                Rectangle b = event.getComponent().getBounds();
                if (b.width < minWidth) {
                    b.width = minWidth;
                }
                if (b.height < minHeight) {
                    b.height = minHeight;
                }
                event.getComponent().setBounds(b.x, b.y, b.width, b.width * minHeight / minWidth);
            }
        });
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     *
     * @link https://stackoverflow.com/a/27711103/6169961
     */
    private void drawCenteredString(Graphics2D g, String text, Rectangle2D.Float rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = (int) ((int) rect.x + (rect.width - metrics.stringWidth(text)) / 2);
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = (int) ((int) rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent());
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Font font = new Font("Arial", Font.PLAIN, 13);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        float pocketWidth = getWidth() / (float) 10;
        float pocketMargin = 20;

        RoundRectangle2D.Float board = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40);
        RoundRectangle2D.Float mancalaPlayerA = new RoundRectangle2D.Float(
                pocketMargin,
                pocketMargin,
                pocketWidth,
                getHeight() - 2 * pocketMargin,
                10,
                10
        );

        RoundRectangle2D.Float mancalaPlayerB = new RoundRectangle2D.Float(
                getWidth() - pocketWidth - pocketMargin,
                pocketMargin,
                pocketWidth,
                getHeight() - 2 * pocketMargin,
                10,
                10
        );

        g2.setColor(theme.getBoardBackgroundColor());
        g2.fill(board);

        g2.setColor(theme.getPocketColor());
        g2.fill(mancalaPlayerA);
        g2.fill(mancalaPlayerB);

        float pocketGridWidth = (getWidth() - (4 * pocketMargin) - 2 * pocketWidth) / 6;
        float midPoint = getHeight() / (float) 2;
        float section = pocketMargin + (midPoint - pocketMargin) / 2;

        g2.drawLine((int) (2 * pocketMargin + pocketWidth), (int) midPoint, (int) (getWidth() - (2 * pocketMargin + pocketWidth)), (int) midPoint);

        g2.setColor(theme.getTextColor());

        float topLeftX, topLeftY;
        for (int i = 0; i < 6; i++) {
            topLeftX = (int) 2 * pocketMargin + pocketWidth + pocketGridWidth * i + (pocketGridWidth - pocketWidth) / 2;
            topLeftY = (int) section - pocketWidth / 2;

            g2.setColor(theme.getTextColor());
            drawCenteredString(g2, String.format("B%d", 6 - i), new Rectangle2D.Float(topLeftX, pocketMargin, pocketWidth, font.getSize()), font);
            drawCenteredString(g2, String.format("%d", i), new Rectangle2D.Float(
                    topLeftX,
                    midPoint,
                    pocketWidth,
                    midPoint - (getHeight() - section - pocketWidth / 2)
            ), font);
            g2.setColor(theme.getPocketColor());
            Ellipse2D.Float pocketB = new Ellipse2D.Float(
                    topLeftX,
                    topLeftY,
                    pocketWidth,
                    pocketWidth
            );

            topLeftX = (int) 2 * pocketMargin + pocketWidth + pocketGridWidth * i + (pocketGridWidth - pocketWidth) / 2;
            topLeftY = (int) getHeight() - section - pocketWidth / 2;
            g2.setColor(theme.getTextColor());
            drawCenteredString(g2, String.format("A%d", i + 1), new Rectangle2D.Float(topLeftX, getHeight() - pocketMargin - font.getSize(), pocketWidth, font.getSize()), font);
            drawCenteredString(g2, String.format("%d", i), new Rectangle2D.Float(
                    topLeftX,
                    midPoint,
                    pocketWidth,
                    -(midPoint - (getHeight() - section - pocketWidth / 2))

            ), font);
            g2.setColor(theme.getPocketColor());
            Ellipse2D.Float pocketA = new Ellipse2D.Float(
                    topLeftX,
                    topLeftY,
                     + pocketWidth,
                    pocketWidth
            );

            g2.fill(pocketB);
            g2.fill(pocketA);
        }
        super.paintComponent(g);
    }

    /**
     * A getter for the theme property.
     *
     * @return the theme instance used by the board
     */
    public BoardTheme getTheme() {
        return theme;
    }

    /**
     * A setter for the theme property
     * @param theme the theme to use when rendering the board
     */
    public void setTheme(BoardTheme theme) {
        this.theme = theme;
        repaint();
    }
}
