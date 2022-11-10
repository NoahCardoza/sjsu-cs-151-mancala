/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.model;

import gui.style.BoardStyle;
import gui.theme.BoardTheme;

public class OptionsModel extends BaseModel {
    private BoardTheme currentTheme;
    private BoardStyle currentStyle;


    public OptionsModel(BoardTheme currentTheme, BoardStyle currentStyle) {
        super();

        this.currentTheme = currentTheme;
        this.currentStyle = currentStyle;
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
}
