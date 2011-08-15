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
import com.jme3.scene.shape.Sphere;


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
        evtManager.addListener(this, SpaceshipAttackEvent.TYPE);
        evtManager.addListener(this, LaserDestroyedEvent.TYPE);
        evtManager.addListener(this, PlanetCreatedEvent.TYPE);
    }

    public void handleEvent(Event event) {
        if (SpaceshipCreatedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipCreatedEvent((SpaceshipCreatedEvent) event);
        } else if (SpaceshipDestroyedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipDestroyedEvent((SpaceshipDestroyedEvent) event);
        } else if (SpaceshipMovedEvent.TYPE.equals(event.getType())) {
            handleSpaceshipMovedEvent((SpaceshipMovedEvent) event);
        } else if (SpaceshipAttackEvent.TYPE.equals(event.getType())) {
            handleSpaceshipAttackEvent((SpaceshipAttackEvent) event);
        } else if (LaserDestroyedEvent.TYPE.equals(event.getType())){
            handleLaserDestroyedEvent((LaserDestroyedEvent) event);
        }
        else if (PlanetCreatedEvent.TYPE.equals(event.getType())){
            handlePlanetCreatedEvent((PlanetCreatedEvent) event);
        }
    }

    private void handleSpaceshipCreatedEvent(SpaceshipCreatedEvent event) {
        String name = event.getSpaceshipName();
        if (rootNode.getChild(name) == null) {
            //TODO: fix spaceship is turned backwards.
            Spatial shipSpatial = assetManager.loadModel("Models/shipA_OBJ/shipA_OBJ.j3o");
            shipSpatial.setName(name);
            shipSpatial.scale(0.1f);
            this.rootNode.attachChild(shipSpatial);
        } else {
            //TODO: ouput error: "spaceship has allready been created"
        }
    }
    private void handlePlanetCreatedEvent (PlanetCreatedEvent event){
          Sphere c = new Sphere(12,12,10);
        Geometry planet = new Geometry(event.getPlanetName(), c);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        planet.setMaterial(mat);
        planet.setName(event.getPlanetName());
        planet.setLocalTranslation(event.getPos());
            this.rootNode.attachChild(planet);
            
    }

    private void handleSpaceshipDestroyedEvent(SpaceshipDestroyedEvent event) {
        if (this.rootNode.detachChildNamed(event.getSpaceshipName()) == -1) {
            //TODO: output error: "laser not found, cannot be removed"
        }
    }

    private void handleSpaceshipMovedEvent(SpaceshipMovedEvent event) {
        Spatial shipSpatial = this.rootNode.getChild(event.getSpaceshipName());
        if (shipSpatial != null) {
            shipSpatial.lookAt(event.getSpaceshipPos(), Vector3f.UNIT_Y);
            shipSpatial.setLocalTranslation(event.getSpaceshipPos());
        } else {
            //TODO: output error: "spaceship not found, cannot be moved"
        }
    }

    private void handleSpaceshipAttackEvent(SpaceshipAttackEvent event) {
        Spatial attacker = this.rootNode.getChild(event.getSpaceshipAttackerName());
        Spatial target = this.rootNode.getChild(event.getSpaceshipTargetName());
        if (attacker == null || target == null) {
            return;
            //TODO: output error: "spaceship not found, cannot be attacked"
        }
        
        Cylinder c = new Cylinder(12, 12, 0.1f, 1f, true);
        Geometry laser = new Geometry(event.getLaserName(), c);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        laser.setMaterial(mat);
        
        Vector3f attackerPos = attacker.getWorldTranslation();
        Vector3f targetPos = target.getWorldTranslation();
        
        float length = targetPos.subtract(attackerPos).length();
        Vector3f centerPos = targetPos.subtract(attackerPos).mult(0.5f).add(attackerPos);
        laser.setLocalScale(1, 1, length);
        laser.setLocalTranslation(centerPos);
        laser.lookAt(targetPos, Vector3f.UNIT_Y);
        laser.setName(event.getLaserName());
        this.rootNode.attachChild(laser);
    }

    private void handleLaserDestroyedEvent(LaserDestroyedEvent event) {
        if (this.rootNode.detachChildNamed(event.getLaserName()) == -1) {
            //TODO: output error: "laser not found, cannot be removed"
        }
    }
}
