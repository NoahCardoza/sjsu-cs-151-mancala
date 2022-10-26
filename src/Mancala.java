/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Mancala implements BoardIcon {
    public static int VARIANT_LEFT = 0;
    public static int VARIANT_RIGHT = 1;
    private RoundRectangle2D.Float mancalaBox;
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
    }

    @Override
    public void resize(int width, int height) {
        float pocketWidth = width / (float) 10;
        float pocketMargin = 20;

        float x = variant == VARIANT_LEFT
                ? pocketMargin
                : width - pocketWidth - pocketMargin;

        mancalaBox = new RoundRectangle2D.Float(
                x,
                pocketMargin,
                pocketWidth,
                height - 2 * pocketMargin,
                10,
                10
        );
    }
}
