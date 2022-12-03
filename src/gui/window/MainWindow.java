/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.window;

import gui.model.ModelManager;
import gui.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Contains all the Main window logic
 * and the different screens.
 */
public class MainWindow extends JFrame {
    /**
     * The enum Card.
     */
    public enum Card {
        /**
         * Main menu card.
         */
        MAIN_MENU,
        /**
         * Game card.
         */
        GAME }

    private final GameView gameView;
    private final CardLayout cardLayout;
    private final MainMenuView mainMenuView;

    /**
     * Instantiates a new Main window.
     *
     * @param modelManager the model manager
     */
    public MainWindow(ModelManager modelManager) {
        super();

        setTitle("Mancala");

        cardLayout = new CardLayout();
        gameView = new GameView(modelManager);
        mainMenuView = new MainMenuView();

        setLayout(cardLayout);
        add(mainMenuView, Card.MAIN_MENU.toString());
        add(gameView, Card.GAME.toString());

        modelManager.getOptionsModel().addEventListener("update:currentCard", event -> {
            showCard(modelManager.getOptionsModel().getCurrentCard());
        });

        // keep the aspect ratio of the screen and enforce a minimum size
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent event) {
                int minWidth = 900;
                int minHeight = 500;

                Rectangle b = event.getComponent().getBounds();
                if (b.width < minWidth) {
                    b.width = minWidth;
                }
                if (b.height < minHeight) {
                    b.height = minHeight;
                }
                event.getComponent().setBounds(b.x, b.y, b.width, b.width * minHeight / minWidth);
            }
        });

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Show card.
     *
     * @param card the card
     */
    public void showCard(Card card) {
        cardLayout.show(getContentPane(), card.toString());
    }

    /**
     * Gets the board view.
     *
     * @return the board view
     */
    public BoardView getBoardView() {
        return gameView.getBoardView();
    }

    /**
     * Gets the options view.
     *
     * @return the options view
     */
    public OptionsView getOptionsView() {
        return gameView.getOptionsView();
    }

    /**
     * Gets the end game state view.
     *
     * @return the end game state view
     */
    public GameOverView getEndGameStateView() {
        return gameView.getEndGameStateView();
    }

    /**
     * Gets the main menu view.
     *
     * @return the main menu view
     */
    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }
}
