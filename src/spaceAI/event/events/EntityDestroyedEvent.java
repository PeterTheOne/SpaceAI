package spaceAI.event.events;

import spaceAI.event.Event;
import spaceAI.event.EventType;

/**
 *
 * @author PeterTheOne
 */
public class EntityDestroyedEvent extends Event {

    public static final EventType TYPE = new EventType("EntityDestroyed");
    
    private String entityName;

    public EntityDestroyedEvent(String entityName) {
        this.entityName = entityName;
    }
    
    public String getEntityName() {
        return this.entityName;
    }
    
    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
