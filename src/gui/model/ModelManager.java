/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.model;

/**
 * Contains references to all the data models in the game to avoid having to pass
 * them around as parameters to so many methods and improve the easy of
 * future refactoring of the code.
 *
 * @author Noah Cardoza
 */
public class ModelManager {
    private final OptionsModel optionsModel;
    private final MancalaModel mancalaModel;

    /**
     * Instantiates a new Model manager.
     *
     * @param optionsModel the options model
     * @param mancalaModel the mancala model
     */
    public ModelManager(OptionsModel optionsModel, MancalaModel mancalaModel) {
        this.optionsModel = optionsModel;
        this.mancalaModel = mancalaModel;
    }

    /**
     * Gets the options model.
     *
     * @return the options model
     */
    public OptionsModel getOptionsModel() {
        return optionsModel;
    }

    /**
     * Gets mancala model.
     *
     * @return the mancala model
     */
    public MancalaModel getMancalaModel() {
        return mancalaModel;
    }
}
