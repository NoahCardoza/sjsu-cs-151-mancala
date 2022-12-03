/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui;

import gui.controller.MainController;
import gui.model.MancalaModel;
import gui.model.ModelManager;
import gui.model.OptionsModel;
import gui.style.BlockBoardStyle;
import gui.style.DefaultBoardStyle;
import gui.style.OblongBoardStyle;
import gui.style.SquareBoardStyle;
import gui.theme.DefaultTheme;
import gui.theme.GreyscaleTheme;
import gui.theme.HackerTheme;
import gui.theme.OceanTheme;
import gui.window.MainWindow;

import java.util.stream.Stream;

/**
 * A class that contains all the logic to set up the GUI window.
 */
public class GUI {
    /**
     * Constructs a new main window and hooks up the models and controllers
     */
    public GUI() {
        ModelManager modelManager = new ModelManager(
                new OptionsModel(
                        Stream.of(
                                new HackerTheme(),
                                new DefaultTheme(),
                                new GreyscaleTheme(),
                                new OceanTheme()
                        ).toList(),
                        Stream.of(
                                new DefaultBoardStyle(),
                                new SquareBoardStyle(),
                                new BlockBoardStyle(),
                                new OblongBoardStyle()
                        ).toList()
                ),
                new MancalaModel()
        );

        MainWindow mainWindow = new MainWindow(modelManager);
        MainController mainController = new MainController(mainWindow, modelManager);

        mainController.addEventListeners();

        mainWindow.setVisible(true);
    }
}
