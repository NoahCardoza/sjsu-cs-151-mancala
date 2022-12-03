/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/14/2022
 * @assignment Mancala
 */

package gui.component;

import gui.model.ModelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The grid view of the pockets.
 */
public class PocketsGridView extends JPanel {
    private final ArrayList<PocketsGridCell> pockets;

    /**
     * Instantiates a new Pockets grid view.
     *
     * @param modelManager the model manager for the game
     */
    public PocketsGridView(ModelManager modelManager) {
        super();

        setOpaque(false);
        setLayout(new GridLayout(2, 6, 10, 10));

        this.pockets = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            PocketsGridCell cell = new PocketsGridCell(modelManager, "B", 12 - i, PocketsGridCell.Variant.TOP);
            pockets.add(cell);
            add(cell);
        }

        for (int i = 0; i < 6; i++) {
            PocketsGridCell cell = new PocketsGridCell(modelManager, "A", i, PocketsGridCell.Variant.BOTTOM);
            pockets.add(cell);
            add(cell);
        }

        modelManager.getMancalaModel().addEventListener("update:pits", (event) -> {
            int[] pits = modelManager.getMancalaModel().getPits();
            for (PocketsGridCell cell: pockets) {
                cell.setCount(pits[cell.getIndex()]);
            }
        });

        modelManager.getMancalaModel().addEventListener("update:currentPlayer", (event) -> {
            switch (modelManager.getMancalaModel().getCurrentPlayer()) {
                case PLAYER_ONE -> {
                    for (int i = 6; i < 12; i++) {
                        pockets.get(i).setActive(true);
                    }
                    for (int i = 0; i < 6; i++) {
                        pockets.get(i).setActive(false);
                    }
                }
                case PLAYER_TWO -> {
                    for (int i = 6; i < 12; i++) {
                        pockets.get(i).setActive(false);
                    }
                    for (int i = 0; i < 6; i++) {
                        pockets.get(i).setActive(true);
                    }
                }
                case TIE -> {
                    for (PocketsGridCell cell: pockets) {
                        cell.setActive(false);
                    }
                }
            }
        }, true);
    }

    /**
     * Add action listener to every pocket cell.
     *
     * @param listener the listener to add to every pocket
     */
    public void addActionListener(ActionListener listener) {
        for (PocketsGridCell cell : pockets) {
            cell.addActionListener(listener);
        }
    }
}
