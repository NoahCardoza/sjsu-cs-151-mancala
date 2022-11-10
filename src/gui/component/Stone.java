/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

package gui.component;

import gui.style.BoardStyle;
import gui.theme.BoardTheme;
import gui.view.BoardView;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.Random;

public class Stone implements BoardIcon {
    private static final Random rand = new Random();
    public final static int COLOR_1 = 0;
    public final static int COLOR_2 = 1;
    public final static int COLOR_3 = 2;
    public final static int COLOR_4 = 3;

    private final BoardView board;
    private final int variant;
    private double size;
    private double pocketSize;
    private boolean active;

    private double rMultiplier;
    private double theta;
    private double pocketCenterOffset;


    private RectangularShape stoneEllipse;

    public Stone(BoardView board, int variant) {
        this.board = board;
        this.variant = variant;
        this.active = false;
        BoardStyle boardStyle = board.getBoardStyle();

        stoneEllipse = boardStyle.getStone(0, 0, 0, 0);
    }

    public void randomlyPlaceInPocketBoundary(double startAngle, double endAngle) {
        // TODO: find a hook to call this function and make sure pocketSize isn't 0
        if (pocketSize > 0) {
            // use polar coordinates to make sure the stones stay inside the boundaries of the pockets
            rMultiplier = rand.nextDouble(0.8 - 0.1) + 0.1;
            double r = pocketCenterOffset * rMultiplier;
            theta = rand.nextDouble(endAngle - startAngle) + startAngle;

            // convert polar coordinates to cartesian
            stoneEllipse = board.getBoardStyle().getStone(
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

        if (active) {
            g2.setColor(theme.getPocketActiveOutlineColor());
        } else {
            g2.setColor(switch (variant) {
                case COLOR_1 -> theme.getStoneColor1Outline();
                case COLOR_2 -> theme.getStoneColor2Outline();
                case COLOR_3 -> theme.getStoneColor3Outline();
                case COLOR_4 -> theme.getStoneColor4Outline();
                default -> throw new IllegalStateException("Unexpected Stone variant value: " + variant);
            });
        }
        g2.draw(stoneEllipse);

        g2.translate(-x, -y);
    }

    @Override
    public void onResize(int width, int height) {
        // TODO: use one function to calculate this somewhere
        size = height / 20F;
        pocketSize = width / 10F;
        BoardStyle boardStyle = board.getBoardStyle();

        pocketCenterOffset = pocketSize / 2;
        double r = pocketCenterOffset * rMultiplier;

        stoneEllipse = boardStyle.getStone(
                pocketCenterOffset - size / 2 + Math.cos(theta) * r,
                pocketCenterOffset - size / 2 + Math.sin(theta) * r,
                size,
                size
        );
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
