package mygame;

import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class SpaceshipCreatedEvent extends Event {
    
    public static final EventType TYPE = new EventType("SpaceshipCreated");
    
    private String spaceshipName;

    public SpaceshipCreatedEvent(String spaceshipName) {
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
