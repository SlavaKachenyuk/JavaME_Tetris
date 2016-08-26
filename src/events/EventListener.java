package events;

/**
 *
 * @author vkachenyuk
 */
public interface EventListener {
    public void handleEvent(Event event, EventDispatcher sender);
}
