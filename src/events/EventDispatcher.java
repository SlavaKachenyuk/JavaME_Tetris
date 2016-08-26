package events;

import java.util.Vector;

/**
 *
 * @author vkachenyuk
 */
public class EventDispatcher {

    private Vector listeners;
    public EventDispatcher() {
        listeners = new Vector();
    }
    
    public void addEventListener(EventListener listener) {
        listeners.addElement(listener);
    }
    
    public void removeEventListener(EventListener listener) {
        listeners.removeElement(listener);
    }
    
    public void dispatchEvent(Event event) {
        for (int i = 0; i < listeners.size(); i++) {
            EventListener listener = (EventListener)listeners.elementAt(i);
            listener.handleEvent(event, this);
        }
    }
}
