/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
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

    private final Ellipse2D.Double stoneEllipse;
    private final Ellipse2D.Double pocketBoundary;

    public Stone(BoardComponent board, int variant) {
        this.board = board;
        this.variant = variant;

        stoneEllipse = new Ellipse2D.Double(0, 0, 0, 0);
        pocketBoundary = new Ellipse2D.Double(0, 0, 0, 0);
    }

    public void randomlyPlaceInPocketBoundary(double startAngle, double endAngle) {
        // TODO: find a hook to call this function and make sure pocketSize isn't 0
        if (pocketSize > 0) {
            double pocketCenterOffset = pocketSize / 2;

            // use polar coordinates to make sure the stones stay inside the boundaries of the pockets
            double r = rand.nextDouble(pocketCenterOffset - 1.5 * size) + size / 2;
            double theta = rand.nextDouble(endAngle - startAngle) + startAngle;

            // convert polar coordinates to cartesian
            stoneEllipse.x = pocketCenterOffset - size / 2 + Math.cos(theta) * r;
            stoneEllipse.y = pocketCenterOffset - size / 2 + Math.sin(theta) * r;
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

        pocketBoundary.height = pocketSize;
        pocketBoundary.width = pocketSize;

        stoneEllipse.height = size;
        stoneEllipse.width = size;
    }
}
