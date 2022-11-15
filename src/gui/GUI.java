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
import gui.style.DefaultBoardStyle;
import gui.theme.HackerTheme;
import gui.window.MainWindow;

public class GUI {
    public GUI() {
        ModelManager modelManager = new ModelManager(
                new OptionsModel(new HackerTheme(), new DefaultBoardStyle()),
                new MancalaModel()
        );

        MainWindow mainWindow = new MainWindow(modelManager);
        MainController mainController = new MainController(mainWindow, modelManager);

        mainController.setup();

        mainWindow.setVisible(true);
    }
}
