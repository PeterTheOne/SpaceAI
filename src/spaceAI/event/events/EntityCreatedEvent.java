package spaceAI.event.events;

import com.jme3.math.Vector3f;
import spaceAI.event.Event;
import spaceAI.event.EventType;

/**
 *
 * @author PeterTheOne
 */
public class EntityCreatedEvent extends Event {

    public static final EventType TYPE = new EventType("EntityCreated");
    
    private String entityType;
    private String entityName;
    private Vector3f entityPos;

    public EntityCreatedEvent(String entityType, String entityName, Vector3f entityPos) {
        this.entityType = entityType;
        this.entityName = entityName;
        this.entityPos = entityPos;
    }
    
    public String getEntityType() {
        return this.entityType;
    }
    
    public String getEntityName() {
        return this.entityName;
    }
    
    public Vector3f getEntityPos(){
        return this.entityPos;
    }
    
    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
