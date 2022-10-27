/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 10/24/2022
 * @assignment Mancala
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pocket implements BoardIcon {
    // TODO: should we create a rand for each class or use a singleton?
    static int VARIANT_UPPER = 0;
    static int VARIANT_LOWER = 1;
    private final BoardComponent board;
    private final String title;
    private final List<Stone> stones;
    private final int index;
    private final int variant;

    /**
     * Cycles through colors when added new stones
     * and cycles through placements when moving stones
     * to the pocket.
     */
    class StoneManager {
        static private final Random rand = new Random();
        static private final int[] stoneColors = {
                Stone.COLOR_1,
                Stone.COLOR_2,
                Stone.COLOR_3,
                Stone.COLOR_4
        };

        private int nextColor = 0;
        private int placeCounter = 0;

        /**
         * Used to randomly distribute the stones into
         * different quadrants.
         */
        private double stoneAngleOffset = rand.nextDouble(Math.PI);

        public Stone factory() {
            Stone stone = new Stone(board, stoneColors[nextColor++ % stoneColors.length]);
            stone.resize(board.getWidth(), board.getHeight());
            return stone;
        }
        public void place(Stone stone) {
            stone.randomlyPlaceInPocketBoundary(stoneAngleOffset, stoneAngleOffset + Math.PI / 4);
            // offset the next round of placement
            if ((++placeCounter % 4) == 0) {
                stoneAngleOffset += Math.PI / 3;
            }
            stoneAngleOffset += Math.PI / 2;
        }
    }

    private Rectangle2D.Float titleTextBox;
    private Rectangle2D.Float stoneCountTextBox;
    private Ellipse2D.Float pocketEllipse;

    private final StoneManager stoneManager;

    Pocket(BoardComponent board, String title, int index, int variant) {
        this.variant = variant;
        this.board = board;
        this.title = title;
        this.index = index;

        stoneManager = new StoneManager();

        // TODO: allow this to be set by
        stones = new ArrayList<>();
        resize(board.getWidth(), board.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        int fontSize = board.getTheme().getFont().getSize();

        float pocketWidth = width / (float) 10;
        float pocketMargin = 20;
        float pocketGridWidth = (width - (4 * pocketMargin) - 2 * pocketWidth) / 6;
        float midPoint = height / (float) 2;
        float section = pocketMargin + (midPoint - pocketMargin) / 2;
        int topLeftX = (int) (2 * pocketMargin + pocketWidth + pocketGridWidth * index + (pocketGridWidth - pocketWidth) / 2);

        float stoneCountTextBoxHeight = midPoint - (board.getHeight() - section - pocketWidth / 2);
        int topLeftY;
        if (this.variant == VARIANT_UPPER) {
            topLeftY = (int) ((int) section - pocketWidth / 2);
            titleTextBox = new Rectangle2D.Float(topLeftX, pocketMargin, pocketWidth, fontSize);
            stoneCountTextBox = new Rectangle2D.Float(
                    topLeftX,
                    midPoint,
                    pocketWidth,
                    stoneCountTextBoxHeight
            );
        } else {
            topLeftY = (int) ((int) height - section - pocketWidth / 2);
            titleTextBox = new Rectangle2D.Float(topLeftX, height - pocketMargin - fontSize, pocketWidth, fontSize);
            stoneCountTextBox = new Rectangle2D.Float(
                    topLeftX,
                    midPoint,
                    pocketWidth,
                    -stoneCountTextBoxHeight
            );
        }

        // TODO: update reference instead of creating new objects
        pocketEllipse = new Ellipse2D.Float(
                topLeftX,
                topLeftY,
                pocketWidth,
                pocketWidth
        );

        // propagate resize event to child components
        for (Stone stone : stones) {
            stone.resize(width, height);
        }
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        BoardTheme theme = board.getTheme();
        Font font = theme.getFont();

        g2.setColor(theme.getTextColor());
        GUIUtils.drawCenteredString(g2, String.format(title), titleTextBox, font);
        GUIUtils.drawCenteredString(g2, String.format("%d", index), stoneCountTextBox, font);

        g2.setColor(theme.getPocketColor());
        g2.fill(pocketEllipse);

        for (Stone stone : stones) {
            stone.draw(g2, (int) pocketEllipse.x, (int) pocketEllipse.y);
        }
    }

    /**
     * Used when moving a stone from one pocket to another.
     *
     * @param stone the stone to add
     */
    public void addStone(Stone stone) {
        stoneManager.place(stone);
        stones.add(stone);
    }

    /**
     * Used when initiating the board to create and add a
     * new stone to the pocket.
     */
    public void addStone() {
        addStone(stoneManager.factory());
    }
}
