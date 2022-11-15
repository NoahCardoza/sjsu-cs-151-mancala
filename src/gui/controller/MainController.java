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
import gui.view.BoardView;
import gui.model.OptionsModel;
import gui.style.*;
import gui.theme.*;
import gui.view.OptionsView;
import gui.window.MainWindow;

import javax.swing.*;
import java.util.List;
import java.util.stream.Stream;

public class MainController implements BaseController {
    private final MainWindow mainWindow;
    private final OptionsModel optionsModel;
    private final MancalaModel mancalaModel;

    private final List<BoardStyle> styles;
    private final List<BoardTheme> themes;
    private final ModelManager modelManager;

    public MainController(MainWindow mainWindow, ModelManager modelManager) {
        this.mainWindow = mainWindow;
        this.modelManager = modelManager;
        // TODO: verify this is safe
        this.optionsModel = modelManager.getOptionsModel();
        this.mancalaModel = modelManager.getMancalaModel();

        styles = Stream.of(
                new DefaultBoardStyle(),
                new SquareBoardStyle(),
                new BlockBoardStyle(),
                new OblongBoardStyle()
        ).toList();

        themes = Stream.of(
                new DefaultTheme(),
                new HackerTheme(),
                new GreyscaleTheme(),
                new OceanTheme()
        ).toList();
    }

    @Override
    public void setup() {
        JComboBox<Object> styleSelect = mainWindow.getOptionsView().getStyleSelect();
        JComboBox<Object> themeSelect = mainWindow.getOptionsView().getThemeSelect();

        styleSelect.setModel(new DefaultComboBoxModel<>(styles.stream().map(BoardStyle::getName).toArray()));
        themeSelect.setModel(new DefaultComboBoxModel<>(themes.stream().map(BoardTheme::getName).toArray()));

        // TODO: if the default in the model is changed select accordingly
        styleSelect.setSelectedIndex(0);
        themeSelect.setSelectedIndex(0);

        addEventListeners();
    }

    @Override
    public void addEventListeners() {
        OptionsView view = mainWindow.getOptionsView();
        BoardView board = mainWindow.getBoardView();

        // on start game button clicked
        mainWindow.getMainMenuView().addStartGameButtonListener(event -> {
            modelManager.getMancalaModel().resetPockets(mainWindow.getMainMenuView().getMancalaCount());
            modelManager.getOptionsModel().setCurrentCard(MainWindow.Card.Game);
        });

        // sets the current style
        view.getStyleSelect().addActionListener((event) -> {
            optionsModel.setCurrentStyle(styles.get(mainWindow.getOptionsView().getStyleSelect().getSelectedIndex()));
        });

        view.getThemeSelect().addActionListener((event) -> {
            optionsModel.setCurrentTheme(themes.get(mainWindow.getOptionsView().getThemeSelect().getSelectedIndex()));
        });

        board.getPocketsView().addActionListener(event -> {
            PocketsGridCell pocket = (PocketsGridCell) event.getSource();
            mancalaModel.moveStones(pocket.getIndex());
        });
    }
}
