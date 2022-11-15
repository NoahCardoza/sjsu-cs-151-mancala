/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.window;

import gui.model.ModelManager;
import gui.view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private final GameView gameView;
    private final CardLayout cardLayout;
    private final MainMenuView mainMenuView;

    public enum Card {
        MainMenu,
        Game
    }

    public MainWindow(ModelManager modelManager) {
        super();

        setTitle("Mancala");

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        gameView = new GameView(modelManager);
        mainMenuView = new MainMenuView(modelManager);

        add(mainMenuView, Card.MainMenu.toString());
        add(gameView, Card.Game.toString());

        mainMenuView.addStartGameButtonListener(e -> showCard(Card.Game));

        setSize(900, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modelManager.getOptionsModel().addEventListener("update:currentCard", event -> {
            showCard(modelManager.getOptionsModel().getCurrentCard());
        });

        // keep the aspect ratio of the screen and enforce a minimum size
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent event) {
                int minWidth = 600;
                int minHeight = 350;

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
    }

    public void showCard(Card card) {
        cardLayout.show(getContentPane(), card.toString());
    }

    public BoardView getBoardView() {
        return gameView.getBoardView();
    }

    public OptionsView getOptionsView() {
        return gameView.getOptionsView();
    }

    public GameView getGameView() {
        return gameView;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }
}
