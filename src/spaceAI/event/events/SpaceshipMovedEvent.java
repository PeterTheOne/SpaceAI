package spaceAI.event.events;

import com.jme3.math.Vector3f;
import spaceAI.event.Event;
import spaceAI.event.EventType;

/**
 *
 * @author PeterTheOne
 */
public class SpaceshipMovedEvent extends Event {
    
    public static final EventType TYPE = new EventType("SpaceshipMoved");
    
    private String spaceshipName;
    private Vector3f spaceshipPos;
    private Vector3f velocity;
    public SpaceshipMovedEvent(String spaceshipName, Vector3f spaceshipPos, Vector3f velocity) {
        this.spaceshipName = spaceshipName;
        this.spaceshipPos = spaceshipPos;
        this.velocity = velocity;
    }
    
    public String getSpaceshipName() {
        return this.spaceshipName;
    }
    
    public Vector3f getSpaceshipPos() {
        return this.spaceshipPos;
    }

    @Override
    public EventType getType() {
        return TYPE;
    }
    public Vector3f getVelocity(){
        return this.velocity;
    }
    
}
