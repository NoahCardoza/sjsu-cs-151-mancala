/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages a collection of changes listeners mapped to
 * a specific events.
 *
 * @author Noah Cardoza
 */
public class EventManager {
    private final HashMap<String, ArrayList<ChangeListener>> listeners;

    /**
     * Instantiates a new Event manager.
     */
    public EventManager() {
        this.listeners = new HashMap<>();
    }

    /**
     * Attached a new listener to an event.
     *
     * @param event    the event
     * @param listener the listener
     */
    public void add(String event, ChangeListener listener) {
        if (!listeners.containsKey(event)) {
            listeners.put(event, new ArrayList<>());
        }

        listeners.get(event).add(listener);
    }

    /**
     * Notifies all listeners of an event.
     *
     * @param event the event
     */
    public void dispatch(String event) {
        // TODO: re-enable to ensure there are no
        //       misconfigured event listeners
        //       find in git log to re-enable
        for (ChangeListener listener : listeners.get(event)) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

}
