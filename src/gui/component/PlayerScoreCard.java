package gui.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerScoreCard extends JPanel {
    private final JLabel playerLabel;
    private final JLabel scoreLabel;
    private String player;
    private int score;

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

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
        playerLabel.setText(player);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText(String.format("Score: %s", score));
    }
}
