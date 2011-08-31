package spaceAI;

import spaceAI.event.events.LaserDestroyedEvent;
import spaceAI.event.events.SpaceshipAttackEvent;
import spaceAI.event.EventManager;

/**
 *
 * @author PeterTheOne
 */
public class Laser {
    
    private float buffer;
    private final float BUFFERSIZE = 1;
    
    private String name;
    private static int count = 0;
    private int number;
    
    private Game game;
    private String spaceshipAttackerName;
    private String spaceshipTargetName;

    public Laser(Game game, String spaceshipAttackerName, String spaceshipTargetName) {
        this.buffer = BUFFERSIZE;
        this.number = this.count++;
        this.name = "Laser#" + number;
        
        this.game = game;
        this.spaceshipAttackerName = spaceshipAttackerName;
        this.spaceshipTargetName = spaceshipTargetName;
        
        EventManager evtManager = this.game.getEventManager();
        SpaceshipAttackEvent event = new SpaceshipAttackEvent(this.name, 
                this.spaceshipAttackerName, this.spaceshipTargetName);
        evtManager.enqueueEvent(event);
    }

    void update(float tpf) {
        if (this.buffer > 0) {
            this.buffer -= tpf;
        } else {
            EventManager evtManager = this.game.getEventManager();
            LaserDestroyedEvent event = new LaserDestroyedEvent(this.spaceshipAttackerName);
            evtManager.enqueueEvent(event);
            this.game.removeLaser(this);
        }
    }
    
}
