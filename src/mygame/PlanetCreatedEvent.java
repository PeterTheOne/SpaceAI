package mygame;

import com.jme3.math.Vector3f;
import event.Event;
import event.EventType;

/**
 *
 * @author PeterTheOne
 */
class PlanetCreatedEvent extends Event {
    
    public static final EventType TYPE = new EventType("PlanetCreated");
    
    private String planetName;
    private Vector3f pos;

    public PlanetCreatedEvent(String spaceshipName, Vector3f pos) {
        this.planetName = spaceshipName;
        this.pos = pos;
        
    }
    
    public String getPlanetName() {
        return this.planetName;
    }
    public Vector3f getPos(){
        return this.pos;
    }
    @Override
    public EventType getType() {
        return TYPE;
    }
    
}
