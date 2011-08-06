/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.math.Quaternion;
import java.util.ArrayList;

/**
 *
 * @author Luciph3r
 */
public class Spaceship {

    private Spatial shipModel;
    private enum moveState{DIRECTION,TARGET};
    moveState MOVESTATE;
    private Vector3f velo;
    private Vector3f dest;
    private ArrayList<Spaceship> seenShips;
    private ShipAI computer;
    
   
    public Spaceship(Spatial shipModel) {
        this.shipModel = shipModel;
        this.velo = new Vector3f();
        this.dest = new Vector3f();
        MOVESTATE = moveState.DIRECTION;
        computer = new ShipAI(this);
        seenShips = new ArrayList<Spaceship>();
    }
    

    public void update(float tpf) {
        if(MOVESTATE == moveState.TARGET){
         if (this.getPos().subtract(dest).length()> 0.01f) {
            this.velo = dest.subtract(getPos()).normalize().mult(3);

        }else{
            velo = Vector3f.ZERO;
        }
        }
        this.computer.think();
         this.shipModel.move(velo.mult(tpf ));
       
    


      
    }
    public void moveDirection(Vector3f direction){
        this.MOVESTATE = moveState.DIRECTION;
        this.velo = direction.normalize();
           this.shipModel.lookAt(velo.add(shipModel.getWorldTranslation()).negate(), Vector3f.UNIT_Y);
    }
    public void moveTo(Vector3f dest) {
         this.MOVESTATE = moveState.TARGET;
                this.shipModel.lookAt(dest.negate(), Vector3f.UNIT_Y);
    }
    public Vector3f getPos(){
      
        return this.shipModel.getWorldTranslation();
    }
    public Quaternion getOrientation(){
          return this.shipModel.getLocalRotation();
    }
    public void addSeenShip (Spaceship ship){
        this.seenShips.add(ship);
    }
    public void clearSeenShips(){
        this.seenShips.clear();
    }
    public boolean seeShip(){
        return !this.seenShips.isEmpty();
    }
    public ArrayList<Spaceship> getSeenShips(){
        return this.seenShips;
    }
    
}
