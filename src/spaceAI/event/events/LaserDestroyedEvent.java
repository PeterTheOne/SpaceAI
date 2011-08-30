package spaceAI.event.events;

import spaceAI.event.Event;
import spaceAI.event.EventType;

/**
 *
 * @author PeterTheOne
 */
public class LaserDestroyedEvent extends Event {
    
    public static final EventType TYPE = new EventType("LaserDestroyed");
    
    private String spaceshipAttackerName;

    public LaserDestroyedEvent(String spaceshipAttackerName) {
       this.spaceshipAttackerName =  spaceshipAttackerName;
    }
    
    public String getSpaceshipAttackerName() {
        return this.spaceshipAttackerName;
    }

    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
