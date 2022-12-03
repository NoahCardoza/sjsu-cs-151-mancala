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

/**
 * The options model. This class is responsible for storing the options for the game.
 */
public class OptionsModel extends BaseModel {
    private final List<BoardTheme> themes;
    private final List<BoardStyle> styles;
    private BoardTheme currentTheme;
    private BoardStyle currentStyle;

    private MainWindow.Card currentCard;

    /**
     * Instantiates a new Options model.
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

        this.currentCard = MainWindow.Card.MAIN_MENU;

        this.currentTheme = themes.get(0);
        this.currentStyle = styles.get(0);
    }

    /**
     * Gets current theme.
     *
     * @return the current theme
     */
    public BoardTheme getCurrentTheme() {
        return currentTheme;
    }

    /**
     * Sets current theme.
     *
     * @param currentTheme the current theme
     */
    public void setCurrentTheme(BoardTheme currentTheme) {
        this.currentTheme = currentTheme;
        dispatchEvent("update:currentTheme");
    }

    /**
     * Gets current style.
     *
     * @return the current style
     */
    public BoardStyle getCurrentStyle() {
        return currentStyle;
    }

    /**
     * Sets current style.
     *
     * @param currentStyle the current style
     */
    public void setCurrentStyle(BoardStyle currentStyle) {
        this.currentStyle = currentStyle;
        dispatchEvent("update:currentStyle");
    }

    /**
     * Gets current card.
     *
     * @return the current card
     */
    public MainWindow.Card getCurrentCard() {
        return currentCard;
    }

    /**
     * Sets current card.
     *
     * @param currentCard the current card
     */
    public void setCurrentCard(MainWindow.Card currentCard) {
        this.currentCard = currentCard;
        dispatchEvent("update:currentCard");
    }

    /**
     * Gets styles.
     *
     * @return the styles
     */
    public List<BoardStyle> getStyles() {
        return styles;
    }

    /**
     * Gets themes.
     *
     * @return the themes
     */
    public List<BoardTheme> getThemes() {
        return themes;
    }
}
