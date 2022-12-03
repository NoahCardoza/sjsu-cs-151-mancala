/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.view;

import gui.component.MancalaView;
import gui.component.PocketsGridView;
import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

/**
 * A component that draws a resizable mancala board.
 *
 * @author Noah Cardoza
 */
public class BoardView extends JPanel {
    // TODO: lock aspect ration in for board (w, h) = (22, 9)
    private final PocketsGridView pocketsGridView;
    private final ModelManager modelManager;

    /**
     * Constructs a mancala game board from a theme
     *
     * @param modelManager holds all the models
     */
    public BoardView(ModelManager modelManager) {
        this.modelManager = modelManager;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(false);
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(gridBagLayout);
        // defaults
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridy = 1;
        c.gridwidth = 1;

        // player A
        c.weightx = 1.0;
        MancalaView mancalaPlayerA = new MancalaView(modelManager, 13);
        gridBagLayout.setConstraints(mancalaPlayerA, c);
        add(mancalaPlayerA);

        // pockets
        c.weightx = 4.0;
        c.insets.left = 10;
        c.insets.right = 10;
        pocketsGridView = new PocketsGridView(modelManager);
        gridBagLayout.setConstraints(pocketsGridView, c);
        add(pocketsGridView);
        c.insets.left = 0;
        c.insets.right = 0;

        // player B
        c.weightx = 1.0;
        MancalaView mancalaPlayerB = new MancalaView(modelManager, 6);
        gridBagLayout.setConstraints(mancalaPlayerB, c);

        add(mancalaPlayerB);

        modelManager.getOptionsModel().addEventListener("update:currentStyle", (event) -> {
            repaint();
        });

        modelManager.getOptionsModel().addEventListener("update:currentTheme", event -> {
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        double width = getWidth();
        double height = getHeight();
        double pocketWidth = width / 10;
        double pocketMargin = 20;
        double midPoint = height / 2;

        RoundRectangle2D.Double board = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40);
        Line2D.Double centerDivide = new Line2D.Double((2 * pocketMargin + pocketWidth), midPoint, width - (2 * pocketMargin + pocketWidth), midPoint);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getBoardBackgroundColor());
        g2.fill(board);

        g2.setColor(modelManager.getOptionsModel().getCurrentTheme().getBoardOutlineColor());
        g2.draw(board);

        g2.draw(centerDivide);
    }

    /**
     * Add action listener to the pockets.
     *
     * @param listener the listener
     */
    public void addPocketActionListener(ActionListener listener) {
        pocketsGridView.addActionListener(listener);
    }
}
