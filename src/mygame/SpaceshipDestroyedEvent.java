package mygame;

import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class SpaceshipDestroyedEvent extends Event {
    
    public static final EventType TYPE = new EventType("SpaceshipDestroyed");
    
    private String spaceshipName;

    public SpaceshipDestroyedEvent(String spaceshipName) {
        this.spaceshipName = spaceshipName;
    }
    
    public String getSpaceshipName() {
        return this.spaceshipName;
    }

    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
