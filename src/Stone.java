/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import mancala.board.style.BoardStyle;
import mancala.board.theme.BoardTheme;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.Random;

public class Stone implements BoardIcon {
    private static final Random rand = new Random();
    public final static int COLOR_1 = 0;
    public final static int COLOR_2 = 1;
    public final static int COLOR_3 = 2;
    public final static int COLOR_4 = 3;

    private final BoardComponent board;
    private final int variant;
    private float size;
    private float pocketSize;

    private RectangularShape stoneEllipse;
    private RectangularShape pocketBoundary;

    public Stone(BoardComponent board, int variant) {
        this.board = board;
        this.variant = variant;
        BoardStyle boardStyle = board.getBoardStyle();

        stoneEllipse = boardStyle.getStone(0, 0, 0, 0);
        pocketBoundary = boardStyle.getPocket(0, 0, 0, 0);
    }

    public void randomlyPlaceInPocketBoundary(double startAngle, double endAngle) {
        // TODO: find a hook to call this function and make sure pocketSize isn't 0
        if (pocketSize > 0) {
            double pocketCenterOffset = pocketSize / 2;

            // use polar coordinates to make sure the stones stay inside the boundaries of the pockets
            double r = rand.nextDouble(pocketCenterOffset - 1.5 * size) + size / 2;
            double theta = rand.nextDouble(endAngle - startAngle) + startAngle;

            // convert polar coordinates to cartesian
            stoneEllipse = board.getBoardStyle().getPocket(
                    pocketCenterOffset - size / 2 + Math.cos(theta) * r,
                    pocketCenterOffset - size / 2 + Math.sin(theta) * r,
                    stoneEllipse.getWidth(),
                    stoneEllipse.getHeight()
            );
        }

    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        BoardTheme theme = board.getTheme();

        g2.translate(x, y);
        g2.setColor(switch (variant) {
            case COLOR_1 -> theme.getStoneColor1();
            case COLOR_2 -> theme.getStoneColor2();
            case COLOR_3 -> theme.getStoneColor3();
            case COLOR_4 -> theme.getStoneColor4();
            default -> throw new IllegalStateException("Unexpected Stone variant value: " + variant);
        });

        g2.fill(stoneEllipse);
        g2.setColor(theme.getPocketOutlineColor());
        g2.draw(stoneEllipse);
        g2.translate(-x, -y);
    }

    @Override
    public void resize(int width, int height) {
        // TODO: use one function to calculate this somewhere
        size = height / (float) 20;
        pocketSize = width / (float) 10;
        BoardStyle boardStyle = board.getBoardStyle();

        pocketBoundary = boardStyle.getPocket(pocketBoundary.getX(), pocketBoundary.getY(), pocketSize, pocketSize);
        stoneEllipse = boardStyle.getPocket(stoneEllipse.getX(), stoneEllipse.getY(), size, size);
    }
}
