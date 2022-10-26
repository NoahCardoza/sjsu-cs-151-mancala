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

public class Pocket implements BoardIcon {
    static int VARIANT_UPPER = 0;
    static int VARIANT_LOWER = 1;
    private final BoardComponent board;
    private final String title;
    private int size;
    private List<Stone> stones;
    private final int index;

    private final int variant;


    private Rectangle2D.Float titleTextBox;
    private Rectangle2D.Float stoneCountTextBox;
    private Ellipse2D.Float pocketEllipse;

    Pocket(BoardComponent board, String title, int index, int variant) {
        this.variant = variant;
        this.board = board;
        this.title = title;
        this.index = index;
        resize(board.getWidth(), board.getHeight());
        stones = new ArrayList<>();
    }

    @Override
    public void resize(int width, int height) {
        int fontSize = board.getTheme().getFont().getSize();

        float pocketWidth = width / (float) 10;
        float pocketMargin = 20;
        float pocketGridWidth = (width - (4 * pocketMargin) - 2 * pocketWidth) / 6;
        float midPoint = height / (float) 2;
        float section = pocketMargin + (midPoint - pocketMargin) / 2;
        int topLeftX = (int) ((int) 2 * pocketMargin + pocketWidth + pocketGridWidth * index + (pocketGridWidth - pocketWidth) / 2);

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

        pocketEllipse = new Ellipse2D.Float(
                topLeftX,
                topLeftY,
                pocketWidth,
                pocketWidth
        );

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
    }
}
