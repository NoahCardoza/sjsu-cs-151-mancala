/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/26/2022
 * @assignment Mancala
 */

package gui.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Represents the scorecard on either side of the
 * game over window for a player.
 */
public class PlayerScoreCard extends JPanel {
    private final JLabel playerLabel;
    private final JLabel scoreLabel;
    private String player;
    private int score;

    /**
     * Instantiates a new player scorecard.
     *
     * @param player the player to generate the card fore
     * @param score  the score to display
     */
    public PlayerScoreCard(String player, int score) {
        setBorder(new EmptyBorder(30, 50, 0, 50));

        Font defaultFont = UIManager.getFont("Label.font");
        Font playerLabelFont = new Font(defaultFont.getName(), Font.BOLD, 22);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playerLabel = new JLabel();
        playerLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        playerLabel.setFont(playerLabelFont);

        scoreLabel = new JLabel();

        add(playerLabel);
        add(scoreLabel);

        setPlayer(player);
        setScore(score);
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(String player) {
        this.player = player;
        playerLabel.setText(player);
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText(String.format("Score: %s", score));
    }
}
