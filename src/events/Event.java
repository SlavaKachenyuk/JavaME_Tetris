package events;

/**
 *
 * @author vkachenyuk
 */
public class Event {

    private final String type;
    
    public Event(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}
