/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import event.Event;
import event.EventListener;
import event.EventManager;


/**
 *
 * @author PeterTheOne
 */
public class View implements EventListener {
    
    private Game game;
    private Node rootNode;
    private AssetManager assetManager;
    private EventManager evtManager;
    
    public View(Game game) {
        this.game = game;
        this.rootNode = this.game.getRootNode();
        assetManager = this.game.getAssetManager();
        evtManager = this.game.getEventManager();
        
        evtManager.addListener(this, SpaceshipCreatedEvent.TYPE);
        evtManager.addListener(this, SpaceshipDestroyedEvent.TYPE);
        evtManager.addListener(this, SpaceshipMovedEvent.TYPE);
    }

    public void handleEvent(Event event) {
        if (SpaceshipCreatedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipCreatedEvent((SpaceshipCreatedEvent) event);
        } else if (SpaceshipDestroyedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipDestroyedEvent((SpaceshipDestroyedEvent) event);
        } else if (SpaceshipMovedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipMovedEvent((SpaceshipMovedEvent) event);
        }
    }

    private void handleSpaceshipCreatedEvent(SpaceshipCreatedEvent event) {
        //TODO: remove
        /*Cylinder c = new Cylinder(12, 12, 0.1f, 1f, true);

        Geometry laser = new Geometry("Cylinder", c);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);

        laser.setMaterial(mat);*/
        String name = event.getSpaceshipName();
        if (rootNode.getChild(name) == null) {
            Spatial shipSpatial = assetManager.loadModel("Models/shipA_OBJ/shipA_OBJ.j3o");
            shipSpatial.setName(name);
            shipSpatial.scale(0.1f);
            this.rootNode.attachChild(shipSpatial);
        } else {
            //TODO: ouput error: "spaceship has allready been created"
        }
    }

    private void handleSpaceshipDestroyedEvent(SpaceshipDestroyedEvent event) {
        this.rootNode.detachChildNamed(event.getSpaceshipName());
    }

    private void handleSpaceshipMovedEvent(SpaceshipMovedEvent event) {
        Spatial shipSpatial = this.rootNode.getChild(event.getSpaceshipName());
        if (shipSpatial != null) {
            shipSpatial.setLocalTranslation(event.getSpaceshipPos());
        } else {
            //TODO: output error: "spaceship not found, cannot be moved"
        }
    }
}
