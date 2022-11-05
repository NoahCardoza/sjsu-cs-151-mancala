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
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A component that draws a resizable mancala board.
 */
public class BoardComponent extends JComponent {
    private BoardTheme theme;
    private BoardStyle boardStyle;
    private final int aspectRatioWidth = 22;
    private final int aspectRatioHeight = 9;
    private final java.util.List<Pocket> pocketsPlayerA;
    private final java.util.List<Pocket> pocketsPlayerB;
    private final Mancala mancalaPlayerA;
    private final Mancala mancalaPlayerB;

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

                // propagate resize event to child components
                mancalaPlayerA.resize(b.width, b.height);
                mancalaPlayerB.resize(b.width, b.height);

                for (Pocket pocket : pocketsPlayerA ) {
                    pocket.resize(b.width, b.height);
                }
                for (Pocket pocket : pocketsPlayerB ) {
                    pocket.resize(b.width, b.height);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(theme.getFont());

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        float pocketWidth = getWidth() / (float) 10;
        float pocketMargin = 20;

        System.out.println(getHeight());

        // TODO: implement board icon to remove flicker on render
        RoundRectangle2D.Float board = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40);

        g2.setColor(theme.getBoardBackgroundColor());
        g2.fill(board);

        // g2.setStroke(new BasicStroke(6));
        g2.setColor(theme.getBoardOutlineColor());
        g2.draw(board);

        mancalaPlayerA.draw(g2);
        mancalaPlayerB.draw(g2);

        int midPoint = getHeight() / 2;

        g2.drawLine((int) (2 * pocketMargin + pocketWidth), midPoint, (int) (getWidth() - (2 * pocketMargin + pocketWidth)), midPoint);


        for (Pocket pocket : pocketsPlayerA) {
            pocket.draw(g2);
        }

        for (Pocket pocket : pocketsPlayerB) {
            pocket.draw(g2);
        }
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
        repaint();
    }

    public List<Pocket> getPocketsPlayerA() {
        return pocketsPlayerA;
    }

    public List<Pocket> getPocketsPlayerB() {
        return pocketsPlayerB;
    }

}
