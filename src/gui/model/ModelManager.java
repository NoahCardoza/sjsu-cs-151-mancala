/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/15/2022
 * @assignment Mancala
 */

package gui.model;

public class ModelManager {
    private final OptionsModel optionsModel;
    private final MancalaModel mancalaModel;

    public ModelManager(OptionsModel optionsModel, MancalaModel mancalaModel) {
        this.optionsModel = optionsModel;
        this.mancalaModel = mancalaModel;
    }

    public OptionsModel getOptionsModel() {
        return optionsModel;
    }

    public MancalaModel getMancalaModel() {
        return mancalaModel;
    }
}
