package mygame;

import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class LaserDestroyedEvent extends Event {
    
    public static final EventType TYPE = new EventType("LaserDestroyed");
    
    private String laserName;

    public LaserDestroyedEvent(String laserName) {
        this.laserName = laserName;
    }
    
    public String getLaserName() {
        return this.laserName;
    }

    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
