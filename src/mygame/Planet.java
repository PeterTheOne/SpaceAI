/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Cylinder;
import event.Event;
import event.EventListener;
import event.EventManager;

/**
 *
 * @author Luciph3r
 */

public class Planet {
    private Game game;
    private String name;
    private Vector3f pos;
    static int num = 0;
    public Planet(Game game,Vector3f pos){
        this.game = game;
        num++;
        this.name = "Planet#"+num;
         EventManager evtManager = this.game.getEventManager();
         this.pos = pos;
       evtManager.enqueueEvent(new PlanetCreatedEvent(this.name,this.pos));
        
  
    }
            
}
