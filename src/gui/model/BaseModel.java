/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.model;

import gui.EventManager;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Abstract class that all models extend from.
 *
 * @author Noah Cardoza
 */
public abstract class BaseModel {
    private final EventManager eventManager;

    /**
     * Instantiates a new Base model.
     */
    public BaseModel() {
        this.eventManager = new EventManager();
    }

    /**
     * Attaches a change listener to an event.
     *
     * @param event    the event
     * @param listener the listener
     */
    public void addEventListener(String event, ChangeListener listener) {
        this.eventManager.add(event, listener);
    }

    /**
     * Attaches a change listener to an event with the option
     * to execute the listener immediately.
     *
     * @param event     the event
     * @param listener  the listener
     * @param immediate the immediate
     */
    public void addEventListener(String event, ChangeListener listener, boolean immediate) {
        addEventListener(event, listener);
        if (immediate) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Dispatches an event to a change listener waiting for the specified event.
     *
     * @param event the event
     */
    protected void dispatchEvent (String event) {
        this.eventManager.dispatch(event);
    }
}
