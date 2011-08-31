/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceAI;

import spaceAI.event.events.EntityCreatedEvent;
import com.jme3.math.Vector3f;
import spaceAI.event.EventManager;

/**
 *
 * @author PeterTheOne
 */
public abstract class Entity {
    
    protected Game game;
    protected String type;
    protected String name;
    protected Vector3f pos;
    
    public Entity(Game game, String type, String name, Vector3f pos) {
        this.game = game;
        this.type = type;
        this.name = name;
        this.pos = pos;
        
        //TODO: enqueue Event in a setUp method.
        EventManager evtManager = this.game.getEventManager();
        evtManager.enqueueEvent(new EntityCreatedEvent(this.type, this.name, this.pos));
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Vector3f getPos() {
        return this.pos;
    }
}
