/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;
import java.awt.geom.RectangularShape;

public class Mancala implements BoardIcon {
    public static int VARIANT_LEFT = 0;
    public static int VARIANT_RIGHT = 1;
    private RectangularShape mancalaBox;
    private final BoardComponent board;
    private final int variant;

    public Mancala(BoardComponent board, int variant) {
        this.board = board;
        this.variant = variant;
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        g2.setColor(board.getTheme().getPocketColor());
        g2.fill(mancalaBox);
        g2.setColor(board.getTheme().getPocketOutlineColor());
        g2.draw(mancalaBox);
    }

    @Override
    public void onResize(int width, int height) {
        int pocketWidth = width / 10;
        int pocketMargin = 20;

        int x = variant == VARIANT_LEFT
                ? pocketMargin
                : width - pocketWidth - pocketMargin;

        mancalaBox = board.getBoardStyle().getMancala(
                x,
                pocketMargin,
                pocketWidth,
                height - 2 * pocketMargin
        );
    }
}
