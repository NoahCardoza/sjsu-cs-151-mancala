/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.view;

import gui.component.PlayerScoreCard;
import gui.model.MancalaModel;
import gui.model.ModelManager;
import gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GameView extends JPanel {
    private final BoardView boardView;
    private final OptionsView optionsView;
    private final CardLayout cardLayout;
    private final GameOverView endGameStateView;


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

    public void showCard(MancalaModel.GameState state) {
        cardLayout.show(this, state.toString());
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }

    public GameOverView getEndGameStateView() {
        return endGameStateView;
    }
}
