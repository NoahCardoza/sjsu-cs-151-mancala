/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.model;

import gui.style.*;
import gui.theme.*;
import gui.window.MainWindow;

import java.util.List;
import java.util.stream.Stream;

public class OptionsModel extends BaseModel {
    private final List<BoardTheme> themes;
    private final List<BoardStyle> styles;
    private BoardTheme currentTheme;
    private BoardStyle currentStyle;

    private MainWindow.Card currentCard;

    /**
     *
     * @param themes all the available themes for the application
     * @param styles all the available styles for the application
     */
    public OptionsModel(
            List<BoardTheme> themes,
            List<BoardStyle> styles
    ) {
        super();
        this.themes = themes;
        this.styles = styles;

        this.currentCard = MainWindow.Card.MainMenu;

        this.currentTheme = themes.get(0);
        this.currentStyle = styles.get(0);
    }

    public BoardTheme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(BoardTheme currentTheme) {
        this.currentTheme = currentTheme;
        dispatchEvent("update:currentTheme");
    }

    public BoardStyle getCurrentStyle() {
        return currentStyle;
    }

    public void setCurrentStyle(BoardStyle currentStyle) {
        this.currentStyle = currentStyle;
        dispatchEvent("update:currentStyle");
    }

    public MainWindow.Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(MainWindow.Card currentCard) {
        this.currentCard = currentCard;
        dispatchEvent("update:currentCard");
    }

    public List<BoardStyle> getStyles() {
        return styles;
    }

    public List<BoardTheme> getThemes() {
        return themes;
    }
}
