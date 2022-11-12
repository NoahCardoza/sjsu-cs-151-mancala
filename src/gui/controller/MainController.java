/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.controller;

import gui.model.MancalaModel;
import gui.view.BoardView;
import gui.component.Pocket;
import gui.model.OptionsModel;
import gui.style.*;
import gui.theme.*;
import gui.view.OptionsView;
import gui.window.MainWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainController implements BaseController {
    private final MainWindow mainWindow;
    private final OptionsModel optionsModel;

    private final List<BoardStyle> styles;
    private final List<BoardTheme> themes;

    private final MancalaModel mancalaModel;

    public MainController(MainWindow mainWindow, OptionsModel optionsModel) {
        this.mainWindow = mainWindow;
        this.optionsModel = optionsModel;

        this.mancalaModel = new MancalaModel();

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

        addChangeListeners();
        addEventListeners();
    }

    @Override
    public void addChangeListeners() {
        mancalaModel.add((event) -> {
            int[] pockets = mancalaModel.getPits();

            System.out.println(Arrays.toString(pockets));

            List<Pocket> pocketsPlayerA = mainWindow.getMainView().getBoard().getPocketsPlayerA();
            List<Pocket> pocketsPlayerB = mainWindow.getMainView().getBoard().getPocketsPlayerB();

            for (int i = 0; i < 6; i++) {
                pocketsPlayerB.get(i).setStoneCount(pockets[12 - i]);
            }

            for (int i = 0; i < 6; i++) {
                pocketsPlayerA.get(i).setStoneCount(pockets[i]);
                
            }

            mainWindow.getMainView().getBoard().repaint();
        });

        optionsModel.addEventListener("update:currentStyle", (event) -> {
            mainWindow.getMainView().getBoard().setBoardStyle(optionsModel.getCurrentStyle());
        });

        optionsModel.addEventListener("update:currentTheme", event -> {
            mainWindow.getMainView().getBoard().setTheme(optionsModel.getCurrentTheme());
        });
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

        List<Pocket> pocketsPlayerA = mainWindow.getMainView().getBoard().getPocketsPlayerA();
        List<Pocket> pocketsPlayerB = mainWindow.getMainView().getBoard().getPocketsPlayerB();

        for (int i = 0; i < 6; i++) {
            int index = i;
            pocketsPlayerB.get(i).addActionListener(event -> {
                mancalaModel.moveStones(12 - index);
            });
        }

        for (int i = 0; i < 6; i++) {
            int index = i;
            pocketsPlayerA.get(i).addActionListener(event -> {
                mancalaModel.moveStones(index);
            });
        }
    }
}
