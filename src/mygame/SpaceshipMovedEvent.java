package mygame;

import com.jme3.math.Vector3f;
import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class SpaceshipMovedEvent extends Event {
    
    public static final EventType TYPE = new EventType("SpaceshipMoved");
    
    private String spaceshipName;
    private Vector3f spaceshipPos;

    public SpaceshipMovedEvent(String spaceshipName, Vector3f spaceshipPos) {
        this.spaceshipName = spaceshipName;
        this.spaceshipPos = spaceshipPos;
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
    
}
