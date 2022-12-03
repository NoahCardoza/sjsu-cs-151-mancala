/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.controller;

import gui.component.PocketsGridCell;
import gui.model.ModelManager;
import gui.window.MainWindow;

import javax.swing.*;

/**
 * The main controller that makes all the
 * model mutations.
 *
 * @author Noah Cardoza
 */
public class MainController {
    private final MainWindow mainWindow;
    private final ModelManager modelManager;

    /**
     * Instantiates a new Main controller.
     *
     * @param mainWindow   the main window
     * @param modelManager the model manager
     */
    public MainController(MainWindow mainWindow, ModelManager modelManager) {
        this.mainWindow = mainWindow;
        this.modelManager = modelManager;
    }

    /**
     * Hooks up all event listeners to the view.
     */
    public void addEventListeners() {
        // on (back to) main menu button clicked
        mainWindow.getOptionsView().addBackToMainMenuActionListener((event) -> {
            // TODO: reset mancala model
            modelManager.getOptionsModel().setCurrentCard(MainWindow.Card.MAIN_MENU);
        });

        // on start game (from main menu) button clicked
        mainWindow.getMainMenuView().addStartGameButtonListener(event -> {
            modelManager.getMancalaModel().resetPockets(mainWindow.getMainMenuView().getMancalaCount());
            modelManager.getOptionsModel().setCurrentCard(MainWindow.Card.GAME);
        });

        // on undo button clicked
        mainWindow.getOptionsView().addUndoActionListener((event) -> {
            modelManager.getMancalaModel().undo();
        });

        // on next turn button clicked
        mainWindow.getOptionsView().addNextTurnActionListener((event) -> {
            modelManager.getMancalaModel().endTurn();
        });

        // on style selected
        mainWindow.getOptionsView().addStyleSelectedListener((event) -> {
            modelManager.getOptionsModel().setCurrentStyle(
                    modelManager
                            .getOptionsModel()
                            .getStyles()
                            .get(((JComboBox<?>) event.getSource()).getSelectedIndex())
            );
        });

        // on theme selected
        mainWindow.getOptionsView().addThemeSelectedListener((event) -> {
            modelManager.getOptionsModel().setCurrentTheme(
                    modelManager
                            .getOptionsModel()
                            .getThemes()
                            .get(((JComboBox<?>) event.getSource()).getSelectedIndex())
            );
        });

        // on pocket clicked
        mainWindow.getBoardView().addPocketActionListener(event -> {
            PocketsGridCell pocket = (PocketsGridCell) event.getSource();
            modelManager.getMancalaModel().moveStonesAtIndex(pocket.getIndex());
        });

        // on play again clicked
        mainWindow.getEndGameStateView().onPlayAgainButtonClick((event) -> {
            modelManager.getOptionsModel().setCurrentCard(MainWindow.Card.MAIN_MENU);
        });

        // on quit clicked
        mainWindow.getEndGameStateView().onQuitButtonClick((event) -> {
            System.exit(0);
        });
    }
}
