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

    public MainController(MainWindow mainWindow, ModelManager modelManager) {
        this.mainWindow = mainWindow;

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
        JComboBox<Object> styleSelect = mainWindow.getMainView().getOptionsView().getStyleSelect();
        JComboBox<Object> themeSelect = mainWindow.getMainView().getOptionsView().getThemeSelect();

        styleSelect.setModel(new DefaultComboBoxModel<>(styles.stream().map(BoardStyle::getName).toArray()));
        themeSelect.setModel(new DefaultComboBoxModel<>(themes.stream().map(BoardTheme::getName).toArray()));

        // TODO: if the default in the model is changed select accordingly
        styleSelect.setSelectedIndex(0);
        themeSelect.setSelectedIndex(0);

        addEventListeners();
    }

    @Override
    public void addEventListeners() {
        OptionsView view = mainWindow.getMainView().getOptionsView();
        BoardView board = mainWindow.getMainView().getBoard();

        view.getStyleSelect().addActionListener((event) -> {
            optionsModel.setCurrentStyle(styles.get(mainWindow.getMainView().getOptionsView().getStyleSelect().getSelectedIndex()));
        });

        view.getThemeSelect().addActionListener((event) -> {
            optionsModel.setCurrentTheme(themes.get(mainWindow.getMainView().getOptionsView().getThemeSelect().getSelectedIndex()));
        });

        // TODO: modify to when user chooses how many stones to play with
        view.getAddStoneButton().addActionListener((event) -> {
            // TODO: keep track of counter or use dropdown select
            mancalaModel.resetPockets(4);
            board.repaint();
        });

        board.getPocketsView().addActionListener(event -> {
            PocketsGridCell pocket = (PocketsGridCell) event.getSource();
            mancalaModel.moveStones(pocket.getIndex());
        });
    }
}
