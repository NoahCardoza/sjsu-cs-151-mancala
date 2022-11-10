/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui;

import gui.controller.MainController;
import gui.model.OptionsModel;
import gui.style.DefaultBoardStyle;
import gui.theme.DefaultTheme;
import gui.window.MainWindow;

public class GUI {
    public GUI() {
        MainWindow mainWindow = new MainWindow();
        OptionsModel optionsModel = new OptionsModel(new DefaultTheme(), new DefaultBoardStyle());
        MainController mainController = new MainController(mainWindow, optionsModel);

        mainController.setup();

        mainWindow.getMainView().setVisible(true);
    }
}
