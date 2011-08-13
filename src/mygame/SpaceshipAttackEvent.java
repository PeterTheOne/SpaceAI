package mygame;

import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class SpaceshipAttackEvent extends Event {
    
    public static final EventType TYPE = new EventType("SpaceshipAttack");
    
    private String laserName;
    private String spaceshipAttackerName;
    private String spaceshipTargetName;

    SpaceshipAttackEvent(String laserName, String spaceshipAttackerName, String spaceshipTargetName) {
        this.laserName = laserName;
        this.spaceshipAttackerName = spaceshipAttackerName;
        this.spaceshipTargetName = spaceshipTargetName;
    }
    
    public String getLaserName() {
        return this.laserName;
    }
    
    public String getSpaceshipAttackerName() {
        return this.spaceshipAttackerName;
    }
    
    public String getSpaceshipTargetName() {
        return this.spaceshipTargetName;
    }

    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
