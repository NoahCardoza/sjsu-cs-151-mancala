/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.controller;

import gui.component.PocketsGridCell;
import gui.model.MancalaModel;
import gui.model.ModelManager;
import gui.window.MainWindow;

import javax.swing.*;

public class MainController implements BaseController {
    private final MainWindow mainWindow;
    private final ModelManager modelManager;

    public MainController(MainWindow mainWindow, ModelManager modelManager) {
        this.mainWindow = mainWindow;
        this.modelManager = modelManager;
    }

    @Override
    public void setup() {
        addEventListeners();
    }

    @Override
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
            modelManager.getMancalaModel().interchange();
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
            MancalaModel.Player player = modelManager.getMancalaModel().getCurrentPlayer();
            int index = pocket.getIndex();

            // TODO: check state to make sure move is possible
            //       and move this logic to the model
            if ((
                    player == MancalaModel.Player.PLAYER_ONE
                            && index <= 5
                            && index >= 0
            ) || (
                    player == MancalaModel.Player.PLAYER_TWO
                            && index <= 12
                            && index >= 7)
            ) {
                modelManager.getMancalaModel().moveStones(index);
            }
        });
    }
}
