/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.view;

import gui.model.MancalaModel;
import gui.model.ModelManager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * The game view containing the board, options, and end of game score.
 *
 * @author Noah Cardoza
 */
public class GameView extends JPanel {
    private final BoardView boardView;
    private final OptionsView optionsView;
    private final CardLayout cardLayout;
    private final GameOverView endGameStateView;


    /**
     * Instantiates a new Game view.
     *
     * @param modelManager the model manager
     */
    public GameView(ModelManager modelManager) {
        // create a new board component

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        JPanel inGameStateView = new JPanel();
        inGameStateView.setLayout(new BorderLayout());

        boardView = new BoardView(modelManager);
        optionsView = new OptionsView(modelManager);

        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.add(boardView);
        setBackground(Color.PINK);

        inGameStateView.add(boardPanel, BorderLayout.CENTER);
        inGameStateView.add(optionsView, BorderLayout.PAGE_END);
        endGameStateView = new GameOverView(modelManager);

        add(inGameStateView, MancalaModel.GameState.IN_GAME.toString());
        add(endGameStateView, MancalaModel.GameState.GAME_OVER.toString());

        modelManager.getMancalaModel().addEventListener("update:state", event -> {
            showCard(modelManager.getMancalaModel().getState());
        });
    }

    /**
     * Show change the screen.
     *
     * @param state the state
     */
    public void showCard(MancalaModel.GameState state) {
        cardLayout.show(this, state.toString());
    }

    /**
     * Gets board view.
     *
     * @return the board view
     */
    public BoardView getBoardView() {
        return boardView;
    }

    /**
     * Gets options view.
     *
     * @return the options view
     */
    public OptionsView getOptionsView() {
        return optionsView;
    }

    /**
     * Gets end game state view.
     *
     * @return the end game state view
     */
    public GameOverView getEndGameStateView() {
        return endGameStateView;
    }
}
