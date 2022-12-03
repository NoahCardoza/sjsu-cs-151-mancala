/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/26/2022
 * @assignment Mancala
 */

package gui.view;

import gui.component.PlayerScoreCard;
import gui.model.MancalaModel;
import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The game over view.
 */
public class GameOverView extends JPanel {
    private final PlayerScoreCard playerOneCard;
    private final PlayerScoreCard playerTwoCard;
    private final JButton playAgainButton;
    private final JButton quitButton;

    /**
     * Instantiates a new game over view.
     *
     * @param modelManager the model manager
     */
    public GameOverView(ModelManager modelManager) {
        setLayout(new BorderLayout());

        Font defaultFont = UIManager.getFont("Label.font");
        Font titleFont = new Font(defaultFont.getName(), Font.BOLD, 36);
        Font subtitleFont = new Font(defaultFont.getName(), Font.PLAIN, 18);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        JLabel winnerLabel = new JLabel("", JLabel.CENTER);
        winnerLabel.setVerticalAlignment(JLabel.TOP);
        winnerLabel.setFont(titleFont);
        winnerLabel.setVerticalAlignment(JLabel.TOP);

        JPanel optionsPanel = new JPanel(new FlowLayout());

        playAgainButton = new JButton("Play Again");
        quitButton = new JButton("Quit");

        optionsPanel.add(playAgainButton);
        optionsPanel.add(quitButton);
        JLabel topLabel = new JLabel("Winner!", JLabel.CENTER);
        topLabel.setFont(subtitleFont);
        topLabel.setBorder(new EmptyBorder(0,0,20,0));

        topLabel.setVerticalAlignment(JLabel.BOTTOM);
        centerPanel.add(topLabel);
        centerPanel.add(winnerLabel);
        centerPanel.add(optionsPanel);
        centerPanel.setBorder(new EtchedBorder());

        playerOneCard = new PlayerScoreCard("Player 1", 0);
        playerTwoCard = new PlayerScoreCard("Player 2", 0);


        JPanel centerCenterPanel = new JPanel(new GridLayout(1,1));
        centerCenterPanel.setBorder(new EmptyBorder(30, 10, 30, 10));
        centerCenterPanel.add(centerPanel);

        add(playerOneCard, BorderLayout.LINE_START);
        add(centerCenterPanel, BorderLayout.CENTER);
        add(playerTwoCard, BorderLayout.LINE_END);

        modelManager.getMancalaModel().addEventListener("update:winner", (event) -> {
            MancalaModel.Player winner = modelManager.getMancalaModel().getWinner();

            if (winner == null) {
                topLabel.setText("");
                winnerLabel.setText("");
                return;
            }

            if (winner == MancalaModel.Player.TIE) {
                topLabel.setText("You both got the same score!");
            } else {
                topLabel.setText("Winner!");
            }
            winnerLabel.setText(switch (winner) {
                case PLAYER_ONE -> "Player 1";
                case PLAYER_TWO -> "Player 2";
                case TIE -> "Tie!";
            });
        });

        modelManager.getMancalaModel().addEventListener("update:pits", (event) -> {
            playerOneCard.setScore(modelManager.getMancalaModel().getPlayerOneScore());
            playerTwoCard.setScore(modelManager.getMancalaModel().getPlayerTwoScore());
        }, true);
    }

    /**
     * On quit button click.
     *
     * @param listener the listener
     */
    public void onQuitButtonClick(ActionListener listener) {
        quitButton.addActionListener(listener);
    }

    /**
     * On play again button click.
     *
     * @param listener the listener
     */
    public void  onPlayAgainButtonClick(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }
}
