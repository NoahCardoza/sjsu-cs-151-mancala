/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import mancala.board.style.BoardStyle;
import mancala.board.theme.BoardTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A component that draws a resizable mancala board.
 */
public class BoardComponent extends JComponent implements BoardIcon {
    private BoardTheme theme;
    private BoardStyle boardStyle;
    private final int aspectRatioWidth = 22;
    private final int aspectRatioHeight = 9;
    private final java.util.List<Pocket> pocketsPlayerA;
    private final java.util.List<Pocket> pocketsPlayerB;
    private final Mancala mancalaPlayerA;
    private final Mancala mancalaPlayerB;

    private RoundRectangle2D.Double board;
    private Line2D.Double centerDivide;

    /**
     * Constructs a mancala game board from a theme
     * @param theme the theme config for the board
     */
    public BoardComponent(BoardTheme theme, BoardStyle boardStyle) {
        this.theme = theme;
        this.boardStyle = boardStyle;

        this.mancalaPlayerA = new Mancala(this, Mancala.VARIANT_RIGHT);
        this.mancalaPlayerB = new Mancala(this, Mancala.VARIANT_LEFT);

        this.pocketsPlayerA = new ArrayList<>();
        this.pocketsPlayerB = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pocketsPlayerA.add(new Pocket(this, String.format("A%d", i + 1), i, Pocket.VARIANT_LOWER));
            pocketsPlayerB.add(new Pocket(this, String.format("B%d", i + 1), i, Pocket.VARIANT_UPPER));
        }

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

                b.height = b.width * minHeight / minWidth;

                event.getComponent().setBounds(b.x, b.y, b.width, b.height);
                BoardComponent.this.setBounds(b.x, b.y, b.width, b.height);

                onResize(b.width, b.height);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g2);
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

    public BoardStyle getBoardStyle() {
        return boardStyle;
    }

    public void setBoardStyle(BoardStyle boardStyle) {
        this.boardStyle = boardStyle;
        // trigger resize to generate the RectangularShape instances
        onResize(getWidth(), getHeight());
        repaint();
    }

    public List<Pocket> getPocketsPlayerA() {
        return pocketsPlayerA;
    }

    public List<Pocket> getPocketsPlayerB() {
        return pocketsPlayerB;
    }

    @Override
    public void onResize(int width, int height) {
        int pocketWidth = width / 10;
        int pocketMargin = 20;
        int midPoint = height / 2;

        board = new RoundRectangle2D.Double(0, 0, width, height, 40, 40);
        centerDivide = new Line2D.Double((2 * pocketMargin + pocketWidth), midPoint, width - (2 * pocketMargin + pocketWidth), midPoint);

        // propagate resize event to child components
        mancalaPlayerA.onResize(width, height);
        mancalaPlayerB.onResize(width, height);

        for (Pocket pocket : pocketsPlayerA ) {
            pocket.onResize(width, height);
        }
        for (Pocket pocket : pocketsPlayerB ) {
            pocket.onResize(width, height);
        }
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        g2.setFont(theme.getFont());

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(theme.getBoardBackgroundColor());
        g2.fill(board);

        g2.setColor(theme.getBoardOutlineColor());
        g2.draw(board);

        mancalaPlayerA.draw(g2);
        mancalaPlayerB.draw(g2);

        g2.draw(centerDivide);


        for (Pocket pocket : pocketsPlayerA) {
            pocket.draw(g2);
        }

        for (Pocket pocket : pocketsPlayerB) {
            pocket.draw(g2);
        }
    }
}
